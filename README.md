
Resumen del Proyecto
Objetivo
El objetivo es crear un sistema que permita a los usuarios buscar, reservar y prestar libros. Se desarrollará un backend utilizando Spring Boot.

Tecnologías
- Java 17
- Spring Boot 3.3.2
- PostgreSQL (base de datos relacional)
- MongoDB (base de datos NoSQL)
- JWT (JSON Web Tokens) para seguridad

Estructura del Proyecto

Modelo de Datos
- Book: Libro con atributos como título, autor y estado.
- User: Usuario con atributos como nombre de usuario, contraseña y roles.
- Loan: Préstamo de un libro a un usuario con atributos como fecha de préstamo y devolución.

Controlador REST
- BookController: Endpoints para crear, obtener, actualizar y eliminar libros.

Servicio de Negocio
- BookService: Lógica de negocio para gestionar las operaciones relacionadas con los libros.

Seguridad
- Autenticación JWT: Los usuarios se autentican con su nombre de usuario y contraseña y reciben un token JWT para acceder a los recursos protegidos.

Pruebas
- Pruebas unitarias para servicios, repositorios y controladores.
- Pruebas de integración para verificar la correcta interacción entre los componentes.

Resultados
- Backend funcional para la gestión de una biblioteca.
- Persistencia de datos en PostgreSQL y MongoDB.
- Seguridad implementada con JWT.
- Pruebas unitarias e integración para garantizar la calidad.

