# Todo API

A production-ready REST API for task management built with **Spring Boot**, **Spring Security**, **JWT Authentication**, and **PostgreSQL**. The API provides secure user authentication and authorization, allowing users to manage their own tasks through RESTful endpoints.

## Features

* User registration and login
* JWT-based authentication and authorization
* Password encryption using BCrypt
* Create, Read, Update, and Delete (CRUD) operations for tasks
* User-specific task access and authorization
* Input validation using Jakarta Validation
* Layered architecture (Controller, Service, Repository)
* PostgreSQL database integration
* RESTful API design

## Tech Stack

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA (Hibernate)
* JWT (JSON Web Token)
* PostgreSQL
* Maven
* Lombok
* Jakarta Validation

## Project Structure

```text
src
├── controller
├── dto
├── entity
├── repository
├── security
├── service
│   └── impl
└── TodoApiApplication.java
```

## API Endpoints

### Authentication

| Method | Endpoint             | Description                       |
| ------ | -------------------- | --------------------------------- |
| POST   | `/api/auth/register` | Register a new user               |
| POST   | `/api/auth/login`    | Authenticate user and receive JWT |

### Tasks

| Method | Endpoint          | Description      |
| ------ | ----------------- | ---------------- |
| POST   | `/api/tasks`      | Create a task    |
| GET    | `/api/tasks`      | Get all tasks    |
| GET    | `/api/tasks/{id}` | Get a task by ID |
| PUT    | `/api/tasks/{id}` | Update a task    |
| DELETE | `/api/tasks/{id}` | Delete a task    |

> **Note:** All task endpoints require a valid JWT Bearer Token.

## Getting Started

### Prerequisites

* Java 21+
* Maven
* PostgreSQL

### Clone the Repository

```bash
git clone https://github.com/AchalNarwade/todo-api.git
cd todo-api
```

### Configure Database

Update your database credentials in:

```properties
application.properties
```

or

```properties
application-secret.properties
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

## Authentication

1. Register a new account.
2. Login to receive a JWT token.
3. Include the token in the request header:

```http
Authorization: Bearer <your-jwt-token>
```

## Testing

The API has been tested using **Postman** for:

* User Registration
* User Login
* Task Creation
* Task Retrieval
* Task Update
* Task Deletion
* Authorization and Authentication

## Future Improvements

* Task filtering and sorting
* Pagination
* Search by title
* Refresh Tokens
* Role-Based Access Control (RBAC)
* Swagger / OpenAPI documentation
* Docker support
* Unit and Integration Testing

## Author

**Achal Narwade**

If you found this project useful, feel free to ⭐ the repository.
