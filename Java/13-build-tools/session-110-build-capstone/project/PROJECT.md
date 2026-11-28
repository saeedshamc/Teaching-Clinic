# پروژهٔ جلسه ۱۱۰ — پروژهٔ کامل با Maven + Gradle

یک پروژهٔ Java کامل بسازید که:
- [ ] `pom.xml` با dependencies (Gson, SQLite, JUnit)
- [ ] `build.gradle` با `plugins`, `repositories`, `dependencies`
- [ ] `User` model با id, name, email, age
- [ ] `UserDao` با JDBC برای database
- [ ] `UserService` برای business logic
- [ ] `Main.java` با REST API endpoints
- [ ] `UserServiceTest.java` با JUnit
- [ ] `mvn package` و `gradlew build` برای build

نمونه:
```
$ mvn package
[INFO] Building jar: target/fullstack-app-1.0.0.jar
$ java -jar target/fullstack-app-1.0.0.jar
=== Full-Stack App ===
Server started on http://localhost:8080
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
