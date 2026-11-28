# جلسه ۹۶ — JDBC Basics

## اهداف یادگیری
- `JDBC` را برای اتصال Java به دیتابیس بشناسید
- `DriverManager`, `Connection`, `Statement`, `ResultSet` را بفهمید
- `SELECT`, `INSERT`, `UPDATE`, `DELETE` را با JDBC اجرا کنید
- `try-with-resources` برای JDBC resources را به‌کار ببرید

## پیش‌نیازها
- فازهای ۱–۵ دورهٔ جاوا

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### JDBC چیست؟

`JDBC` (Java Database Connectivity) API استاندارد جاوا برای اتصال به دیتابیس‌های رابطه‌ای (MySQL, PostgreSQL, SQLite, Oracle) است. در `java.sql` package قرار دارد و بدون نیاز به dependency خارجی کامپایل می‌شود — فقط driver دیتابیس در runtime لازم است.

### مراحل اتصال

```
۱. Driver registration (خودکار در JDBC 4+)
۲. DriverManager.getConnection(url, user, password)
۳. connection.createStatement()
۴. statement.executeQuery / executeUpdate
۵. ResultSet برای خواندن نتایج
۶. بستن resources
```

### Connection URL

```java
// MySQL
"jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC"

// PostgreSQL
"jdbc:postgresql://localhost:5432/mydb"

// SQLite (فایلی — برای تست بدون سرور!)
"jdbc:sqlite:mydb.db"
```

### CRUD با Statement

```java
Connection conn = DriverManager.getConnection(url, user, pass);
Statement stmt = conn.createStatement();

// SELECT
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
while (rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
}

// INSERT/UPDATE/DELETE — تعداد rows affected
int rows = stmt.executeUpdate("INSERT INTO users(name) VALUES('Ali')");
```

### try-with-resources — الزامی

`Connection`, `Statement`, `ResultSet` همه `AutoCloseable` هستند:

```java
try (Connection conn = DriverManager.getConnection(url, user, pass);
     Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
    while (rs.next()) {
        System.out.println(rs.getString("name"));
    }
}
```

### SQLite برای تست بدون سرور

برای تمرین، [SQLite JDBC driver](https://github.com/xerial/sqlite-jdbc) را دانلود و در classpath بگذارید — دیتابیس فایلی است و نیازی به نصب سرور ندارد.

## کد مهم

`src/JdbcBasics.java` — CRUD کامل با try-with-resources.
`src/schema.sql` — اسکیمای نمونه.

## خطاهای رایج
- فراموش کردن بستن `Connection` → connection leak.
- `executeQuery` برای INSERT استفاده کردن → `SQLException` — از `executeUpdate` استفاده کنید.
- `ResultSet` را بدون `next()` استفاده کردن → اولین رکورد خوانده نمی‌شود.
- driver jar را در classpath نداشتن → `ClassNotFoundException` / `No suitable driver`.

## جمع‌بندی
- JDBC = API استاندارد دیتابیس در جاوا.
- `Connection` → `Statement` → `ResultSet` → close.
- همیشه `try-with-resources`.
- **جلسهٔ بعد:** PreparedStatement — جلوگیری از SQL Injection.
