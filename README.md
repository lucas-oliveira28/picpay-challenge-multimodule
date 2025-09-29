# PicPay Challenge (Multi‑module)

A multi‑module Java 17 project that exposes a REST API for registering users and creating money transactions between them. The application uses an in‑memory H2 database, integrates with external authorization and notification services via OpenFeign, and separates concerns into distinct Maven modules: application (API), core (domain/use cases), database (JPA entities/repositories), and integration modules (authorization, notification).

## Tech stack
- Language: Java 17
- Build/Package Manager: Apache Maven (multi‑module)
- Frameworks/Libraries:
  - Spring Boot 3.5.5 (Web, Validation)
  - Spring Data JPA 3.5.5
  - Spring Cloud OpenFeign 4.3.0
  - H2 Database 2.3.x (in‑memory)
  - MapStruct 1.5.x
  - Lombok 1.18.x
  - Jackson 2.19.x

## Modules
- application: Spring Boot REST API (entry point). Depends on core + integration modules.
- core: Domain DTOs, ports (commands/integrations), use cases, mappers, exception handling. Depends on database.
- database: JPA entities and repositories.
- integration/authorization: Feign client + mappers/operations to call external authorization API.
- integration/notification: Feign client + mappers/operations to call external notification API.

## Entry point
- Main class: application/src/main/java/io/github/lucasoliveira28/App.java
- Spring Boot component scan: io.github.lucasoliveira28 (covers all modules)

## Requirements
- JDK 17+
- Maven 3.9+
- Internet access (for dependency resolution and external API calls)

## Configuration
Application configuration is in application/src/main/resources/application.yml
- spring.application.name: PicPayChallengeMultimoduleAPI
- In‑memory H2 datasource: jdbc:h2:mem:testdb (username: sa, no password)
- JPA: show SQL, format SQL, ddl‑auto: update
- H2 console: available at /h2-console
- Server port: not explicitly set (defaults to 8080)

External integrations (hard‑coded base URLs in Feign clients):
- Authorization API: https://util.devi.tools/api/v2 (GET /authorize)
- Notification API: https://util.devi.tools/api/v1 (POST /notify)

TODO:
- Externalize the Feign base URLs to configuration (application.yml/env vars) instead of hard‑coding.

## Build
From the project root:
- Compile and install all modules: mvn clean install

Notes:
- The project does not currently declare the Spring Boot Maven Plugin in application/pom.xml. This means the produced application-1.0-SNAPSHOT.jar may not be an executable (fat) jar. You can still run using the plugin by specifying it on the command line (see Run section), or add the plugin to the POM (see TODO below).

## Run
Option A — Run via Spring Boot Maven Plugin without modifying POM:
- mvn -pl application -am org.springframework.boot:spring-boot-maven-plugin:3.5.5:run

Option B — (Recommended) Add the Spring Boot Maven Plugin to application/pom.xml, then use:
- mvn -pl application spring-boot:run
- Or build and run the executable jar: mvn -pl application package and then java -jar application/target/application-1.0-SNAPSHOT.jar

TODO:
- Add spring-boot-maven-plugin to application/pom.xml to generate an executable jar and enable simpler run commands.

## API Endpoints
Base path: /api

Users
- POST /api/user/new
  - Body (JSON):
    {
      "fullName": "string (min 10)",
      "cpf": "string (CPF)",
      "email": "string (email)",
      "password": "string (min 6)",
      "userType": "string",
      "balance": number
    }
  - Response 201 (JSON):
    {
      "id": "UUID",
      "fullName": "string",
      "cpf": "string",
      "email": "string",
      "userType": "string",
      "balance": number
    }

- GET /api/user/{id}
  - Response 200 (JSON): UserResponseDTO as above

- DELETE /api/user/{id}
  - Response 200 (JSON): UserResponseDTO as above

Transactions
- POST /api/transaction/new
  - Body (JSON):
    {
      "value": number,
      "payerId": "UUID",
      "payeeId": "UUID"
    }
  - Response 200 (JSON):
    {
      "id": "UUID",
      "value": number,
      "payer": { /* user summary */ },
      "payee": { /* user summary */ }
    }

- GET /api/transaction/{id}
  - Response 200 (JSON): TransactionResponseDTO as above

Notes:
- Validation constraints are applied (e.g., CPF format, email, min lengths, non‑null fields). Errors return standard Spring Boot validation error responses with project‑specific exception handling in core.

## Sample cURL
- Create user:
  curl -X POST http://localhost:8080/api/user/new \
       -H "Content-Type: application/json" \
       -d '{
            "fullName": "John Doe Example",
            "cpf": "123.456.789-09",
            "email": "john@example.com",
            "password": "secret1",
            "userType": "USER",
            "balance": 100.0
          }'

- Create transaction:
  curl -X POST http://localhost:8080/api/transaction/new \
       -H "Content-Type: application/json" \
       -d '{
            "value": 25.0,
            "payerId": "<PAYER_UUID>",
            "payeeId": "<PAYEE_UUID>"
          }'


## Project structure
picpay-challenge-multimodule/
- pom.xml (parent)
- application/
  - pom.xml
  - src/main/java/io/github/lucasoliveira28/App.java (main class)
  - src/main/resources/application.yml (Spring config)
  - controllers, DTOs, mappers
- core/
  - pom.xml
  - domain DTOs, ports (command/integration), use cases, exception handling, mappers
- database/
  - pom.xml
  - entities (User, Transaction, UserType) and repositories
- integration/
  - authorization/ (Feign client, mapper, operation)
  - notification/ (Feign client, mapper, operation)

## Scripts / common Maven commands
From repository root:
- Build all modules: mvn clean install
- Run app (plugin specified inline): mvn -pl application -am org.springframework.boot:spring-boot-maven-plugin:3.5.5:run
- Clean: mvn clean
