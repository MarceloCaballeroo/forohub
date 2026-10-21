# ForoHub

API REST de un foro en línea, hecha con **Spring Boot 3 y Java 17**. Gestiona tópicos (preguntas y debates por curso) con operaciones CRUD, validaciones, migraciones de base de datos y autenticación con **JWT**.

Proyecto final de la especialización **Backend** del programa Oracle Next Education (ONE, Alura Latam), diciembre de 2024.

## Qué incluye

- **CRUD de tópicos** con paginación (20 por página por defecto) y estado del tópico (`NO_RESPONDIDO`, `RESPONDIDO`, `CERRADO`).
- **Autenticación con JWT:** `POST /login` entrega un token firmado (HMAC256) con expiración configurable; el resto de los endpoints exige `Authorization: Bearer <token>`.
- **Spring Security sin estado** (`STATELESS`) y contraseñas almacenadas con **BCrypt**.
- **Validaciones** con Hibernate Validator y un manejador global de errores, que además evita registrar tópicos duplicados (mismo título y mensaje).
- **Migraciones con Flyway** para crear las tablas `usuario` y `topico`, con clave foránea al autor.
- **Sin secretos en el código:** la base de datos y el JWT se configuran por variables de entorno.

## Tecnologías

Java 17 · Spring Boot 3.4 · Spring Web · Spring Data JPA · Spring Security · java-jwt (Auth0) · MySQL · Flyway · Hibernate Validator · Lombok · Maven

## Endpoints

| Método | Ruta | Descripción | Requiere token |
|---|---|---|---|
| `POST` | `/login` | Autenticación; devuelve el JWT | No |
| `POST` | `/topicos` | Crea un tópico | Sí |
| `GET` | `/topicos` | Lista tópicos con paginación | Sí |
| `GET` | `/topicos/{id}` | Detalle de un tópico | Sí |
| `PUT` | `/topicos/{id}` | Actualiza un tópico | Sí |
| `DELETE` | `/topicos/{id}` | Elimina un tópico | Sí |

Cuerpo de `/login`:

```json
{ "login": "usuario", "clave": "contraseña" }
```

## Modelo de datos

```
usuario (id, login, clave)
topico  (id, titulo, mensaje, fecha_creacion, status, autor_id → usuario.id, curso)
```

## Cómo ejecutarlo

Requiere Java 17, Maven y una base de datos MySQL.

```bash
git clone https://github.com/MarceloCaballeroo/forohub.git
cd forohub
```

Define las variables de entorno:

| Variable | Uso |
|---|---|
| `DB_URL` | URL JDBC, por ejemplo `jdbc:mysql://localhost:3306/forohub` |
| `DB_USERNAME` | Usuario de la base de datos |
| `DB_PASSWORD` | Contraseña de la base de datos |
| `JWT_SECRET` | Clave con la que se firman los tokens |
| `JWT_EXPIRATION` | Duración del token, en horas |

```bash
mvn spring-boot:run
```

Flyway crea las tablas al iniciar. La API queda en `http://localhost:8080`. Para usarla, inserta un usuario con su clave cifrada con BCrypt, obtén el token en `/login` y envíalo en el encabezado `Authorization`.

## Alcance

Es un proyecto formativo: cubre el ciclo completo de una API segura (modelo, migraciones, validación, autenticación y manejo de errores). No incluye todavía pruebas automatizadas más allá de la prueba de arranque, registro de usuarios por API ni control de permisos por rol.

## Autor

Marcelo Caballero Olave · [LinkedIn](https://www.linkedin.com/in/marcelo-caballero-olave) · [Portafolio](https://portafolio.marcelo-caballero.workers.dev/)
