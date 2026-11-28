# پروژهٔ جلسه ۱۱۶ — پروژهٔ Spring Boot با REST API

یک پروژهٔ Spring Boot بسازید که:
- `MyAppApplication` با `@SpringBootApplication`
- `UserController` با `@RestController` برای CRUD
- `UserService` برای business logic
- `application.properties` برای config
- `pom.xml` با `spring-boot-starter-web`
- `spring-boot-maven-plugin` برای packaging

نمونه:
```
$ mvn spring-boot:run
=== Spring Boot App Started ===
$ curl http://localhost:8080/api/users
[{"id":1,"name":"Ali"},...]
```

## صورت پروژه
پاسخ در `solution/` — MyAppApplication.java + UserController.java + pom.xml.
