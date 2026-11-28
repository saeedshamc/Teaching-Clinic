# جلسه ۹۷ — PreparedStatement و SQL Injection

## اهداف یادگیری
- `PreparedStatement` را برای parameterized queries بفهمید
- تفاوت `Statement` و `PreparedStatement` را تشخیص دهید
- SQL Injection را بشناسید و جلوگیری کنید
- `setNull`, `getGeneratedKeys`, batch updates را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۹۶ (JDBC Basics)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### چرا PreparedStatement؟

```java
// خطرناک — SQL Injection!
String name = "Ali'; DROP TABLE users; --";
String sql = "SELECT * FROM users WHERE name = '" + name + "'";
stmt.executeQuery(sql);  // 💥 TABLE DELETED

// امن — parameterized
String sql = "SELECT * FROM users WHERE name = ?";
PreparedStatement ps = conn.prepareStatement(sql);
ps.setString(1, name);   // مقدار به‌عنوان DATA، نه CODE
ps.executeQuery();       // ✅ امن
```

### انواع set methods

```java
ps.setString(1, "text");
ps.setInt(2, 42);
ps.setLong(3, 1234567890123L);
ps.setDouble(4, 3.14);
ps.setBoolean(5, true);
ps.setDate(6, java.sql.Date.valueOf("2024-01-15"));
ps.setNull(7, Types.VARCHAR);   // برای NULL
```

### Batch Updates — برای insert/update/delete گروهی

```java
String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
try (PreparedStatement ps = conn.prepareStatement(sql)) {
    for (User u : users) {
        ps.setString(1, u.getName());
        ps.setString(2, u.getEmail());
        ps.addBatch();   // اضافه به batch
    }
    int[] results = ps.executeBatch();  // یکجا اجرا
}
```

### Generated Keys — گرفتن id ساخته‌شده

```java
try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    ps.setString(1, name);
    ps.executeUpdate();
    try (ResultSet keys = ps.getGeneratedKeys()) {
        if (keys.next()) {
            int newId = keys.getInt(1);  // id تولیدشده
        }
    }
}
```

### CallableStatement — برای Stored Procedures

```java
try (CallableStatement cs = conn.prepareCall("{call get_user_age(?)}")) {
    cs.setInt(1, userId);
    ResultSet rs = cs.executeQuery();
}
```

## کد مهم

`src/PreparedStatementDemo.java` — مقایسهٔ Statement vs PreparedStatement + batch + generated keys.

## خطاهای رایج
- Concatenating user input در SQL → SQL Injection.
- `?` placeholder را فراموش کردن یا index را اشتباه زدن.
- `executeBatch` را بدون `addBatch` استفاده کردن.
- `setNull` را بدون `Types` استفاده کردن → `SQLException`.

## جمع‌بندی
- همیشه `PreparedStatement` — هرگز string concatenation برای SQL.
- `addBatch`/`executeBatch` برای عملیات گروهی — خیلی سریع‌تر.
- `RETURN_GENERATED_KEYS` برای گرفتن id auto-generated.

**جلسهٔ بعد:** Connection Pool.
