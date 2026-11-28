# پاسخ تمرین‌های Employee Management System

## آسان

**۱ و ۲.** اجرا: `java -cp out EmployeeManagementSystem`. فایل `employees.csv`
در همان پوشهٔ اجرا ساخته می‌شود. جستجو در `EmployeeService.search()` روی
نام، ایمیل و دپارتمان `contains` می‌کند.

## متوسط

**۳.** خروجی CSV با `CsvExporter.export()` — فایل با header و سطرهای
`Employee.toRow()` نوشته می‌شود.

**۴.** ذخیره‌سازی در `EmployeeDAO.saveAll()` — همهٔ لیست هر بار بازنویسی
می‌شود؛ پس با اجرای مجدد، داده‌ها باقی می‌مانند.

**۵.** `delete()` فقط `active=false` می‌کند و `getAll()` فیلتر
`isActive` دارد — رکورد در فایل می‌ماند ولی نمایش داده نمی‌شود.

## سخت

**۶.**
```java
table.setAutoCreateRowSorter(true);   // در MainFrame بعد از new JTable
```

**۷.**
```java
// در EmployeeService
public void restore(Long id) {
    employees.stream().filter(e -> e.getId().equals(id))
        .forEach(e -> e.setActive(true));
    dao.saveAll(employees);
}
```

**۸.** می‌توانید از `JTextArea` + `JOptionPane` برای گزارش متنی استفاده کنید:
```java
StringBuilder report = new StringBuilder();
service.countByDepartment().forEach((d, n) ->
    report.append(d).append(": ").append(n).append(" نفر\n"));
JOptionPane.showMessageDialog(this, report.toString());
```
