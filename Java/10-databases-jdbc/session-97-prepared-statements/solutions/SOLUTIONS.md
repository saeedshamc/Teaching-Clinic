# پاسخ تمرین‌های جلسه ۹۷

## آسان

**۱ و ۲.**
```java
String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
try (PreparedStatement ps = conn.prepareStatement(sql)) {
    ps.setString(1, "Ali");
    ps.setString(2, "ali@mail.com");
    ps.executeUpdate();

    ps.setString(1, "Sara");
    ps.setNull(2, Types.VARCHAR);  // email = NULL
    ps.executeUpdate();
}
```

## متوسط

**۳ و ۴.**
```java
for (User u : users) {
    ps.setString(1, u.getName());
    ps.addBatch();
}
int[] results = ps.executeBatch();

PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
ps.executeUpdate();
ResultSet keys = ps.getGeneratedKeys();
if (keys.next()) int id = keys.getInt(1);
```

## سخت

**۵ و ۶.**
```java
// امن — هرگز concatenate نکنید
PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE name = ?");
ps.setString(1, userInput);

// CallableStatement
CallableStatement cs = conn.prepareCall("{call get_user_age(?)}");
cs.setInt(1, userId);
ResultSet rs = cs.executeQuery();
```
