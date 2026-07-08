# 01 - Hello Application

The first project in my Spring Boot learning journey. This project demonstrates how to create and run a simple REST API using Spring Boot.

---

## Tech Stack

- Java
- Spring Boot
- Maven
- Spring Web

---

## Project Structure

```text
src
└── main
    ├── java
    │   └── com.yash.hello_application
    │       ├── controller
    │       └── HelloApplication.java
    └── resources
```

---

## Annotations Used

### `@SpringBootApplication`
- Marks the main Spring Boot application.
- Enables auto-configuration.
- Performs component scanning.
- Allows Java-based configuration.

### `@RestController`
- Marks the class as a REST controller.
- Returns data directly as the HTTP response.

### `@RequestMapping("/api")`
- Defines the base URL for all endpoints in the controller.

### `@GetMapping("/hello")`
- Maps HTTP GET requests to the `/hello` endpoint.

---

## API Endpoint

| Method | Endpoint | Response |
|--------|----------|----------|
| GET | `/api/hello` | `Hello MySelf` |

---

## Application Entry Point

The `main()` method is the starting point of the application.

### Why `String[] args`?

- Receives command-line arguments when the application starts.
- Spring Boot uses these arguments for runtime configuration (for example, changing the server port or activating a profile).
- Java defines the application entry point as `String[] args` because command-line arguments are always passed as text.

---

## Concepts Learned

- Spring Boot Project Structure
- Spring Boot Application
- REST Controller
- Request Mapping
- GET Mapping
- First REST API
- Application Entry Point

---

## Output

```
GET http://localhost:8080/api/hello

Hello MySelf
```