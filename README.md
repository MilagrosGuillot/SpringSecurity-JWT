📌 Este proyecto es una aplicación Java Spring Boot para una plataforma en línea de servicios profesionales que conecta a usuarios con profesionales en
diversas áreas. La plataforma tiene como objetivo ofrecer una experiencia segura y eficiente, facilitando la contratación de profesionales calificados para tareas específicas. Permite a los usuarios buscar,
contratar y calificar a profesionales en diferentes áreas.
Se ha desarrollado e implementado un sistema de autenticación utilizando Spring Security y tokens JWT para todos los usuarios y se definió cuatro roles de usuario: Usuario Normal, Profesional, Administrador y Soporte, cada uno con diferentes
permisos de acceso.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
### Listado de Entidades
**Profesional** 
- id
- username
- password
- Correo
- DNI

**Usuario**
- id
- username
- password
- Correo
- isEnabled
- accountNoExpired
- accountNoLocked
- credentialNoExpired
- profesional
- roles

**Rol**
- id
- rolEnum
- permissionList

**CategoriasDeTrabajo**
- id
- categorias
- Descripcion

**Calificaciones**
- id
- opinion
- calificacion

**Permisos**
- id
- name

### ENDPOINTS

📍 GET | /profesionals
- Obtiene una lista de profesionales.

📍 GET | /users
- Obtiene una lista de usuarios.

📍 POST | /sign-up
- Registra un usuario en la base de datos.

📍 PUT | /login
- Permite al usuario iniciar sesión en la aplicación.

### Diagrama

### Modelo Relacional de la BD

![ServiciosProfesionales drawio](https://github.com/MilagrosGuillot/SpringSecurity-JWT/assets/101240663/98687187-3394-4026-bf1c-2ac82039d49b)



