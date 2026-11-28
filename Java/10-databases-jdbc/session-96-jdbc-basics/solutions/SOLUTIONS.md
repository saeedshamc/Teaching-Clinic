# پاسخ تمرین‌های جلسه ۹۶

## آسان

**۱ و ۲.**
```java
try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
     Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
    while (rs.next()) {
        System.out.println(rs.getString("name"));
    }
}
```

## متوسط

**۳ و ۴.**
```java
try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
     PreparedStatement ps = conn.prepareStatement("INSERT INTO users(name) VALUES(?)")) {
    ps.setString(1, "Ali");
    ps.executeUpdate();
}

ResultSet rs = stmt.executeQuery("SELECT * FROM users");
while (rs.next()) { /* rs.getString("name") */ }
```

## سخت

**۵ و ۶.**
```java
try (Connection conn = DriverManager.getConnection(url);
     PreparedStatement ps = conn.prepareStatement(sql)) {
    ps.setString(1, name);
    int rows = ps.executeUpdate();
    System.out.println("Rows affected: " + rows);
}
```
