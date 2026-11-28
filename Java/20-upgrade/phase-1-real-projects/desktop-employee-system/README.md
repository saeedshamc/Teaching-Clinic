# Employee Management System — پروژهٔ واقعی دسکتاپ

سیستم مدیریت کارکنان با Java Swing که **واقعاً قابل اجراست**.
ذخیره‌سازی روی فایل، جستجو، فیلتر، خروجی CSV و آمار.

## اجرا

```bash
javac -d out src/*.java
java -cp out EmployeeManagementSystem
```

## ساختار

```
src/
├── EmployeeManagementSystem.java   # نقطهٔ ورود
├── Employee.java                   # مدل داده
├── EmployeeService.java            # منطق برنامه
├── EmployeeDAO.java                # ذخیره/بازیابی فایل
├── MainFrame.java                  # پنجرهٔ اصلی
├── EmployeeDialog.java             # دیالوگ افزودن/ویرایش
└── CsvExporter.java                # خروجی CSV
```

## تمرین‌ها
`exercises/EXERCISES.md` — آسان/متوسط/سخت
`solutions/SOLUTIONS.md` — پاسخ با توضیح

## صورت پروژهٔ توسعه
`project/PROJECT.md` — نسخهٔ کامل‌تر (گزارش‌گیری، نمودار، چندکاربره)
`project/solution/` — پاسخ
