# پروژهٔ جلسه ۱۲۱ — پروژهٔ Spring Boot با Actuator و Profiles

یک پروژهٔ Spring Boot با Actuator و Profiles بسازید که:
- `spring-boot-starter-actuator` برای monitoring
- `CustomHealthIndicator` برای custom health checks
- `AppInfoContributor` برای `/actuator/info`
- `@Profile` برای environment-specific beans
- `application-dev.properties` و `application-prod.properties`
- `management.endpoints.web.exposure.include` برای endpoint exposure

نمونه:
```
GET /actuator/health → {"status":"UP","details":{"db":"UP","cache":"UP"}}
GET /actuator/info → {"app":{"name":"MyApp","version":"1.0.0"}}
GET /actuator/metrics → JVM metrics
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
