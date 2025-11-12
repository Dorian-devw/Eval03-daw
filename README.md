# 🍽️ Sistema de Gestión de Restaurante - Restaurant Management

Proyecto desarrollado con **Spring Boot** para la gestión integral de un restaurante, permitiendo administrar **clientes, pedidos, platos, insumos, usuarios, facturación y auditoría de acciones**.

------------------------------------------------------------

## 🚀 Características principales

- 🔐 **Autenticación y autorización con Spring Security**
  - Roles de usuario: ADMIN, MOZO, COCINERO, CAJERO.
  - Inicio de sesión con validación de credenciales encriptadas con BCrypt.
  - Control de acceso según rol en cada módulo.

- 👥 **Gestión de clientes**
  - Registro, edición, eliminación y listado.
  - Campos: DNI, nombres, apellidos, teléfono y correo.

- 🍽️ **Gestión de platos e insumos**
  - Administración de platos del menú (nombre, tipo, precio, descripción).
  - Control de insumos con stock, unidad de medida, precio de compra, etc.
  - Asociación N-N entre platos e insumos.

- 📦 **Gestión de pedidos**
  - Creación de pedidos asociados a clientes y mesas.
  - Descuento automático de insumos al generar un pedido.
  - Estado del pedido: pendiente, en proceso, entregado, etc.

- 🧾 **Facturación**
  - Generación automática de factura al completar un pedido.
  - Detalle de conceptos, montos y métodos de pago.

- 🧍 **Gestión de usuarios**
  - Administración de usuarios con roles y estado.
  - Auditoría de acciones mediante AOP (registro en tabla bitacora).

- 🎨 **Interfaz moderna**
  - Uso de Thymeleaf y Bootstrap 5.
  - Páginas responsivas y con diseño intuitivo.
  - Animaciones suaves en el login y un panel principal con tarjetas interactivas.

------------------------------------------------------------

## 🧩 Tecnologías utilizadas

Backend: Java 17, Spring Boot 3
Seguridad: Spring Security, BCrypt
Frontend: Thymeleaf, Bootstrap 5
Base de datos: MySQL
ORM: JPA / Hibernate
Herramientas: Maven, Spring DevTools

------------------------------------------------------------

## 📁 Estructura del proyecto

restaurant-management/
├── src/
│   ├── main/
│   │   ├── java/com/sinca/restaurant_management/
│   │   │   ├── controller/         -> Controladores MVC
│   │   │   ├── entity/             -> Entidades JPA
│   │   │   ├── repository/         -> Repositorios Spring Data
│   │   │   ├── service/            -> Servicios de negocio
│   │   │   ├── security/           -> Configuración y lógica de seguridad
│   │   │   └── aop/                -> Auditoría con AOP
│   │   └── resources/
│   │       ├── static/             -> Archivos CSS, JS, imágenes
│   │       └── templates/          -> Vistas Thymeleaf (por módulo)
│   └── test/                       -> Pruebas unitarias
└── pom.xml                         -> Dependencias Maven

------------------------------------------------------------

## ⚙️ Configuración del entorno

1. Clona el repositorio:
   git clone https://github.com/tuusuario/restaurant-management.git

2. Importa el proyecto en tu IDE (IntelliJ, Eclipse o VS Code con soporte Maven).

3. Crea la base de datos ejecutando el script `restaurant_db.sql` incluido en la carpeta raíz.

4. Configura las credenciales de acceso a tu base de datos en `application.properties`:

   spring.datasource.url=jdbc:mysql://localhost:3306/restaurant_db
   spring.datasource.username=root
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update

5. Ejecuta la aplicación con:
   mvn spring-boot:run

6. Accede desde tu navegador a:
   http://localhost:8081

------------------------------------------------------------

## 🔑 Credenciales iniciales

Usuario administrador por defecto (según el script SQL):

Usuario: admin
Contraseña: admin123

------------------------------------------------------------

## 🧱 Base de datos (MySQL)

El archivo `restaurant_db.sql` incluye todas las tablas necesarias:
- usuario, cliente, mesa, insumo, plato, plato_insumo
- pedido, detalle_pedido, factura, detalle_factura
- proveedor, compra, detalle_compra
- bitacora (para registro de auditoría).

Archivo completo en formato SQL incluido en el repositorio.

------------------------------------------------------------

## 💡 Roles y permisos

| Rol       | Acceso principal |
|------------|------------------|
| ADMIN      | Todos los módulos |
| MOZO       | Pedidos, mesas y clientes |
| COCINERO   | Pedidos y cocina |
| CAJERO     | Ventas y facturación |

------------------------------------------------------------

## 🧾 Auditoría con AOP

Cada acción importante (crear, editar, eliminar) se registra automáticamente en la tabla `bitacora`, indicando:
- Usuario responsable
- Acción ejecutada
- Fecha y hora
- Detalles del registro afectado

------------------------------------------------------------

## 🎨 Interfaz visual

- Diseño con **Bootstrap 5** y paleta de colores personalizada.
- Navbar fijo con botón de cierre de sesión.
- Página principal (index) con tarjetas para acceder a cada módulo:
  - Clientes
  - Platos
  - Insumos
  - Pedidos
  - Usuarios
- Formulario de login animado y responsivo.

------------------------------------------------------------

## 🧑‍💻 Autores

Proyecto desarrollado por **Frank Sinca**  
Estudiante de **Diseño y Desarrollo de Software - TECSUP (2025)**

------------------------------------------------------------

## 🪪 Licencia

Este proyecto es de uso académico y libre bajo la licencia **MIT**.

Puedes modificarlo, distribuirlo o ampliarlo con fines educativos o profesionales.
