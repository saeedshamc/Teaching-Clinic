# پاسخ تمرین‌های جلسه ۱۴۴

## آسان

**۱ و ۲.**
```dockerfile
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## متوسط

**۳ و ۴.**
```yaml
services:
  app:
    build: .
    ports: ["8080:8080"]
    depends_on: [db]
  db:
    image: postgres:15
```
```bash
docker run -p 8080:8080 myapp:latest
```

## سخت

**۵ و ۶.**
```dockerfile
ENV JAVA_OPTS="-XX:+UseG1GC -Xms256m -Xmx512m"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
```
```bash
docker-compose up -d
docker-compose logs -f app
```
