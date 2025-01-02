# Foro Hub - ONE Oracle

Foro Hub es una API REST desarrollada con **Spring Boot**, enfocada en la gestión de foros en línea. Esta API permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los tópicos y cuenta con un sistema de autenticación y autorización robusto.

---

## Tecnologías Empleadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **MySQL** (Base de datos relacional)
- **Flyway** (Migración de base de datos)
- **JWT** (Json Web Tokens para autenticación)
- **Maven** (Gestor de dependencias)

---

## Guía de Instalación y Configuración


# Ejecución del Proyecto

## Clona el repositorio:

```bash
https://github.com/MarceloCaballeroo/forohub.git
```

## Accede al directorio del proyecto:

```bash
cd forohub
```

## Inicia el proyecto:

```bash
mvn spring-boot:run
```
La API estará disponible en http://localhost:8080.

---

## Principales Endpoints

- **Crear un Tópico**
  - Método: `POST /topicos`

- **Actualizar un Tópico**
  - Método: `PUT /topicos/{id}`

- **Eliminar un Tópico**
  - Método: `DELETE /topicos/{id}`

- **Listar Todos los Tópicos**
  - Método: `GET /topicos`

- **Obtener Información de un Tópico Específico**
  - Método: `GET /topicos/{id}`

---

## Migraciones con Flyway
Las migraciones de la base de datos son gestionadas a través de Flyway. Los scripts SQL se encuentran en el directorio src/main/resources/db/migration.

---

## Validaciones Implementadas

La API utiliza **Hibernate Validator** para asegurar la integridad de los datos. Ejemplos de validaciones:

- Los campos como título, mensaje y autor no pueden estar vacíos (`@NotBlank`).
- El campo de estado debe ser un valor válido del enum `StatusTopic` (`@NotNull`).

---

## Seguridad

- La autenticación se basa en usuarios mediante **User  Details** y **User  DetailsService**.
- Se implementa **JWT** (Json Web Tokens) para la autenticación de usuarios.
- Configuraciones necesarias en `application.properties`:

```properties
api.security.secret=${JWT_SECRET}
api.security.expiration=${JWT_EXPIRATION}
```
