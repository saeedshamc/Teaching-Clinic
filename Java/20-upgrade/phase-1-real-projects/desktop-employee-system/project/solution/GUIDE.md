# پاسخ پروژهٔ Employee System توسعه‌یافته

این فایل چک‌لیست است — پیاده‌سازی کامل به عهدهٔ دانشجوست.
نکات کلیدی برای هر بخش:

## گزارش‌گیری پیشرفته
```java
// میانگین حقوق هر دپارتمان
Map<String, Double> avgByDept = employees.stream()
    .filter(Employee::isActive)
    .collect(Collectors.groupingBy(Employee::getDepartment,
        Collectors.averagingDouble(Employee::getSalary)));

// نمودار میله‌ای — JPanel سفارشی
class BarChart extends JPanel {
    private final Map<String, Double> data;
    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // رسم مستطیل برای هر دپارتمان
    }
}
```

## چندقابلیتی
```java
JTabbedPane tabs = new JTabbedPane();
tabs.addTab("کارکنان", employeePanel);
tabs.addTab("گزارش‌ها", reportPanel);
tabs.addTab("تنظیمات", settingsPanel);
```

## پشتیبان‌گیری
```java
Files.copy(Paths.get("employees.csv"),
    Paths.get("backup/employees_" + LocalDate.now() + ".csv"));
```

## ورود گروهی CSV
```java
// خواندن فایل انتخابی با BufferedReader و split(",")
// سپس service.create(...) برای هر سطر
```

## تم
```java
UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
SwingUtilities.updateComponentTreeUI(this);
```
