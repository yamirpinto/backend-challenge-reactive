# Backend Challenge Reactive

## Descripción

API REST reactiva construida con Spring Boot 3, WebFlux y R2DBC, que realiza:

- Suma de dos números y aplicación de un porcentaje dinámico (cacheado 30 min).
- Mock de porcentaje externo.
- Registro histórico de llamadas en PostgreSQL.
- Endpoints:
  - `POST /calculate`
  - `GET /history`
  - `GET /mock-percentage`

## Tecnologías

- Java 21
- Spring Boot 3.x (WebFlux, Data R2DBC)
- PostgreSQL en Docker
- Caffeine Cache
- Docker / Docker Compose
- Swagger/OpenAPI (springdoc)
- Gradle (Groovy DSL)
- JUnit 5, Mockito, Reactor Test

## Requisitos previos

- Docker Desktop (Windows, Mac, Linux)
- [Opcional] JDK 21 y Gradle para build local

## Compilar localmente (opcional)

```bash
./gradlew clean bootJar
```

El JAR generado estará en `build/libs/backend-challenge-reactive-0.0.1-SNAPSHOT.jar`.

## Levantar con Docker Compose

Desde la raíz del proyecto:

```bash
docker compose up --build -d
```

- Contenedor `postgres` con la BD `api_historial` y tabla `history` inicializada.
- Contenedor `api` con la aplicación Spring Boot.

Para detener y borrar contenedores y volúmenes:

```bash
docker compose down -v
```

## Endpoints

### Mock porcentaje

```http
GET /mock-percentage
```

**Respuesta**

```json
{ "percentage": 10.0 }
```

### Cálculo dinámico

```http
POST /calculate
Content-Type: application/json

{ "num1": 100, "num2": 50 }
```

**Respuesta**

```json
{
  "num1": 100.0,
  "num2": 50.0,
  "percentageUsed": 10.0,
  "result": 165.0
}
```

### Historial de llamadas

```http
GET /history?page=0&size=10
```

**Respuesta**

```json
[
  {
    "id": "...",
    "timestamp": "...",
    "endpoint": "/calculate",
    "requestBody": "{\"num1\":100,\"num2\":50}",
    "responseBody": "{...}",
    "errorMessage": null
  },
  ...
]
```

## Swagger UI

Una vez levantada la API, accede a:

```
http://localhost:8080/swagger-ui.html
```

o bien:

```
http://localhost:8080/swagger-ui/index.html
```

## Ejecutar tests

```bash
./gradlew test
```

Incluye tests unitarios para:

- Lógica de cálculo y cache de porcentaje.
- Servicio de historial (persistencia R2DBC).

## Publicar imagen en Docker Hub (opcional)

```bash
docker build -t <usuario>/backend-challenge:latest .
docker push <usuario>/backend-challenge:latest
```

