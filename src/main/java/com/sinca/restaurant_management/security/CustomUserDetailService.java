package com.sinca.restaurant_management.security;

import com.sinca.restaurant_management.entity.Usuario;
import com.sinca.restaurant_management.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new User(user.getNombreUsuario(), user.getPassword(),
                user.getEstado(),
                true, true, true,
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRol())));
    }
}
