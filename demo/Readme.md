## Quiz 5

Sistema de gestión de envíos y paquetes desarrollado en Java 21 con Spring Boot, estructurado bajo una arquitectura en capas, seguridad basada en roles (RBAC con JWT), manejo global de errores y pruebas unitarias.

---

## 1.Arquitectura Desacoplada
La aplicación implementa un modelo de arquitectura desacoplada cliente-servidor orientada a servicios RESTful. 
Las capas están estrictamente divididas (domain, data, business, controller, config, exception).



## 2.Especificaciones
Java Version:JDK 21
Framework: Spring Boot 3.2.5
Seguridad:Spring Security con autenticación basada en tokens JWT y contraseñas cifradas


##3. Instrucciones de Ejecución Local

### Prerrequisitos
Tener instalado ava JDK 21 Maven.

### Pasos para levantar el proyecto
1. Clonar el repositorio y abrir la carpeta raíz en tu terminal o en Visual Studio Code.
2. Compilar y ejecutar la aplicación usando Maven Wrapper:./mvnw spring-boot:run
     


## 3. Ejecución de Pruebas Unitarias
Para correr las pruebas unitarias automatizadas con JUnit 5 y Mockito (PSTest), ejecuta el siguiente comando en la terminal:
mvnw test