# تمرین جلسه ۴۰ — ساخت پروژه وب

## تمرین ۱ — لایه‌بندی Appointment (آسان)

سیستم نوبت‌دهی کلینیک با لایه‌های Domain، Application و Infrastructure (InMemory) بسازید:

- Entity: `Appointment` (Id, PatientName, Date, IsConfirmed)
- Interface: `IAppointmentRepository`
- Service: `IAppointmentService` با `Book` و `GetUpcoming`
- Controller شبیه‌سازی: `AppointmentsController`

---

## تمرین ۲ — DTO و Configuration (متوسط)

1. `Product` entity با فیلدهای `InternalNotes` (محرمانه) بسازید
2. `ProductPublicDto` فقط Id, Name, Price
3. Mapper بنویسید
4. `AppConfiguration` با override Development تست کنید

---

## تمرین ۳ — DI Container (چالشی)

Container ساده با پشتیبانی از:
- `AddSingleton<TInterface, TImpl>`
- `AddScoped<TInterface, TImpl>`
- Constructor injection (resolve خودکار)

---

**راهنما:** [exercise_01.cs](./exercise_01.cs) | [exercise_02.cs](./exercise_02.cs)
