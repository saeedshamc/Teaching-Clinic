# پروژهٔ جلسه ۱۰۸ — پروژهٔ Maven با Dependencies

یک پروژهٔ Maven بسازید که:
- `pom.xml` با `groupId`, `artifactId`, `version`
- Dependencies: Guava (compile), JUnit (test)
- `MyApp.java` با main method
- `MyAppTest.java` برای unit tests
- `mvn compile`, `mvn test`, `mvn package` commands

نمونه:
```
$ mvn compile
[INFO] BUILD SUCCESS
$ mvn test
[INFO] Tests run: 3, Failures: 0, Errors: 0
$ mvn package
[INFO] Building jar: target/myapp-1.0.0.jar
```

## صورت پروژه
پاسخ در `solution/` — pom.xml + MyApp.java + MyAppTest.java.
