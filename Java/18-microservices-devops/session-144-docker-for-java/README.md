# جلسه ۱۴۴ — Docker for Java: Containerization

## اهداف یادگیری
- `Docker` را برای containerization بشناسید
- `Dockerfile` برای Java applications بنویسید
- `docker build`, `docker run`, `docker-compose` را استفاده کنید
- `Multi-stage build` برای optimized images بسازید

## پیش‌نیازها
- جلسهٔ ۱۴۳ (Security Capstone)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Docker برای Java

`Docker` — containerization برای Java applications. Isolated environment با dependencies.

### Dockerfile — basic

```dockerfile
# Build stage
FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Multi-stage build — optimized

```dockerfile
# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-XX:+UseG1GC", "-Xms256m", "-Xmx512m", "-jar", "app.jar"]
```

### docker-compose.yml — multi-container

```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
    depends_on:
      - db
      - redis

  db:
    image: postgres:15
    environment:
      - POSTGRES_DB=mydb
      - POSTGRES_USER=user
      - POSTGRES_PASSWORD=pass
    volumes:
      - db-data:/var/lib/postgresql/data

  redis:
    image: redis:7-alpine
    ports:
      - "6379:6379"

volumes:
  db-data:
```

### Docker commands

```bash
# Build image
docker build -t myapp:latest .

# Run container
docker run -p 8080:8080 myapp:latest

# Run with environment
docker run -e SPRING_PROFILES_ACTIVE=dev -p 8080:8080 myapp:latest

# Docker Compose
docker-compose up -d
docker-compose down
docker-compose logs -f app
```

## کد مهم

`src/Dockerfile` — Java application Dockerfile.
`src/docker-compose.yml` — multi-container setup.
`src/Application.java` — Spring Boot application.
`src/application.properties` — application config.

## خطاهای رایج
- `COPY` را بدون `WORKDIR` استفاده کردن → files in wrong location.
- `mvn package` را بدون `dependency:go-offline` استفاده کردن → slow builds.
- `java -jar` را بدون `-Xmx` استفاده کردن → OOM in container.
- `EXPOSE` را بدون `-p` در `docker run` استفاده کردن → port not accessible.

## جمع‌بندی
- Docker برای containerization — isolated environment.
- `Dockerfile` برای image creation — multi-stage build.
- `docker-compose` برای multi-container apps.
- `-Xms`, `-Xmx` برای memory tuning in containers.

**جلسهٔ بعد:** CI/CD.
