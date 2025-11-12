package com.sinca.restaurant_management.aop;

import com.sinca.restaurant_management.entity.Bitacora;
import com.sinca.restaurant_management.entity.Usuario;
import com.sinca.restaurant_management.repository.BitacoraRepository;
import com.sinca.restaurant_management.repository.UsuarioRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    private final BitacoraRepository bitacoraRepository;
    private final UsuarioRepository usuarioRepository;

    public AuditAspect(BitacoraRepository bitacoraRepository, UsuarioRepository usuarioRepository) {
        this.bitacoraRepository = bitacoraRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Around("@annotation(com.sinca.restaurant_management.aop.Auditable)")
    public Object audit(ProceedingJoinPoint pjp) throws Throwable {
        Object result;
        String accion;
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Auditable ann = signature.getMethod().getAnnotation(Auditable.class);
        accion = ann.value().isEmpty() ? signature.getName() : ann.value();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = null;
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            String username = auth.getName();
            usuario = usuarioRepository.findByNombreUsuario(username).orElse(null);
        }
        // Ejecutar método
        try {
            result = pjp.proceed();
            // guardar bitacora OK
            Bitacora b = new Bitacora(usuario, accion + " - OK", buildDetails(pjp));
            bitacoraRepository.save(b);
        } catch (Throwable ex) {
            Bitacora b = new Bitacora(usuario, accion + " - ERROR", buildDetails(pjp) + "\nEX: " + ex.getMessage());
            bitacoraRepository.save(b);
            throw ex;
        }
        return result;
    }

    private String buildDetails(ProceedingJoinPoint pjp) {
        StringBuilder sb = new StringBuilder();
        sb.append("Clase: ").append(pjp.getSignature().getDeclaringTypeName()).append("; ");
        sb.append("Metodo: ").append(pjp.getSignature().getName()).append("; ");
        Object[] args = pjp.getArgs();
        sb.append("Args: ");
        if (args == null || args.length == 0) sb.append("[]");
        else {
            for (Object a : args) {
                sb.append(a == null ? "null" : a.toString()).append(" | ");
            }
        }
        return sb.toString();
    }
}
