# ssp-game-api

Small service implementing a Stone-Scissors-Paper (ssp) game with two move strategies:
- `AI` — move chosen by calling an external AI provider (OpenAI via Spring AI).
- `RANDOM` — deterministic or random local strategy (no external call).

This README covers configuration, running (Maven and Docker), observability (Swagger, OpenAPI, Actuator), endpoints, examples, and deployment notes.

## Tech stack
- Java 21
- Spring Boot 3.5.6
- Springdoc OpenAPI / Swagger UI
- Spring AI / OpenAI provider (optional, configured via env var)
- Maven
- Docker / Docker Compose

## Configuration
- Environment variables:
    - `OPEN_AI_KEY` — OpenAI API key example:
        - `OPEN_AI_KEY=sk-REPLACE_WITH_YOUR_KEY`
- Local config file: `src/main/resources/application-dev.yml` reads the key from the environment.
- Additional Spring properties and actuator endpoints can be set in `application.yml` / `application-dev.yml`.

## Build & Run (Maven)
1. Build:
    - `mvn clean package -DskipTests`
2. Run (local profile; ensure `OPEN_AI_KEY` is set in your shell when using `AI` strategy):
    - `mvn spring-boot:run -Dspring-boot.run.profiles=dev`
3. Run tests:
    - `mvn test`

## Docker
- With Docker Compose (recommended for local development):
    - `docker compose up --build`  \- this forces rebuild of images before starting containers.
    - Make sure set `OPEN_AI_KEY` in the application-dev.yml or use an env file.

## Observability & Docs
- Swagger UI (Springdoc):
    - `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON:
    - `http://localhost:8080/v3/api-docs`
- Actuator endpoints:
    - `http://localhost:8080/actuator/health`
    - Enable additional endpoints in `application.yml` as needed.

## API Endpoints (summary)
- POST `/api/v1/ssp/play`
    - Purpose: Play one round. Client supplies the player move and desired level. Server returns chosen move by computer and round result.
    - Request JSON:
      ```json
      {
        "playerMove": "STONE",
        "level": "EASY" 
      }
      ```
    - Curl example:
      ```bash
      curl -X POST http://localhost:8080/api/v1/ssp/play \
        -H "Content-Type: application/json" \
        -d '{"playerMove":"STONE","level":"EASY"}'
      ```
    - Example response (JSON):
      ```json
      {
        "computerMove": "PAPER",
        "result": "LOSE"
      }
      ```
    - Notes:
        - When `level` is `HARD`, the service will call the OpenAI provider (requires `OPEN_AI_KEY`).
        - When `level` is `EASY`, no external call is made and a local algorithm determines the computer move.

## Error handling & HTTP statuses
- `200` OK — success
- `400` Bad Request — invalid input (e.g., unknown move or missing fields)
- `500` Internal Server Error — unexpected server error

## Security
- `OPEN_AI_KEY` is sensitive. Never commit it to source control.



