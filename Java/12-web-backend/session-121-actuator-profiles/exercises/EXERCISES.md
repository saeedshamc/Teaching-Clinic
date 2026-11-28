# تمرین‌های جلسه ۱۲۱ — Actuator & Profiles

## آسان
1. `spring-boot-starter-actuator` dependency اضافه کنید.
2. `application-dev.properties` و `application-prod.properties` بسازید.

## متوسط
3. `CustomHealthIndicator` برای custom health check پیاده کنید.
4. `@Profile("dev")` و `@Profile("prod")` برای beans استفاده کنید.

## سخت
5. `AppInfoContributor` برای `/actuator/info` استفاده کنید.
6. `management.endpoints.web.exposure.include` برای endpoint exposure تنظیم کنید.
