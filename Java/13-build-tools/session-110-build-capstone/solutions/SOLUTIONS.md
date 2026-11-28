# پاسخ تمرین‌های جلسه ۱۱۰

## آسان

**۱ و ۲.**
```xml
<dependencies>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.10.1</version>
    </dependency>
</dependencies>
```
```groovy
dependencies {
    implementation 'com.google.code.gson:gson:2.10.1'
}
```

## متوسط

**۳ و ۴.**
```java
public class UserService {
    private List<User> users = new ArrayList<>();
    public User create(String name, String email, int age) { ... }
    public Optional<User> findById(int id) { ... }
}

public class UserDao {
    private Connection conn;
    public void insert(User user) throws SQLException { ... }
    public List<User> findAll() throws SQLException { ... }
}
```

## سخت

**۵ و ۶.**
```java
// REST API with com.sun.net.httpserver
HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
server.createContext("/users", exchange -> {
    // handle GET/POST/PUT/DELETE
});
server.start();

// JUnit test
@Test
public void testCreateUser() {
    UserService service = new UserService();
    User user = service.create("Ali", "ali@mail.com", 25);
    assertNotNull(user);
}
```
