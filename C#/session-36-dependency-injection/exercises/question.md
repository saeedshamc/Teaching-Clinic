# تمرین‌های جلسه ۳۶ — Dependency Injection

## کلاسی (۳۰ دقیقه)

1. `INotificationSender` + `EmailSender` + `SmsSender`
2. `NotificationService` با constructor injection
3. Composition Root برای wiring

## خانه (۴۵ دقیقه)

4. `Microsoft.Extensions.DependencyInjection` + register/resolve
5. Singleton cache + Transient validator
6. Unit test با fake repository

## Setup

```powershell
dotnet add package Microsoft.Extensions.DependencyInjection
```

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs)
