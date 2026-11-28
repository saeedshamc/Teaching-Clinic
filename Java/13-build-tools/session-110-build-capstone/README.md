# جلسه ۱۱۰ — Build Capstone: پروژهٔ کامل با Maven + Gradle

## اهداف یادگیری
- Maven و Gradle را برای یک پروژهٔ واقعی ترکیب کنید
- `pom.xml` و `build.gradle` را برای یک اپ کامل تنظیم کنید
- Dependencies، plugins، testing را integrate کنید
- CI/CD-ready build configuration را بسازید

## پیش‌نیازها
- جلسات ۱۰۸–۱۰۹

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### پروژه: Full-Stack Java Application

یک پروژهٔ Java کامل بسازید که:

**Features:**
- REST API با `com.sun.net.httpserver` (built-in) یا Spring Boot
- Database با JDBC + SQLite
- JSON serialization با Gson
- Unit tests با JUnit
- Build با Maven و Gradle

### Maven vs Gradle — کدام؟

| Feature | Maven | Gradle |
|---|---|---|
| Configuration | XML (pom.xml) | Groovy/Kotlin DSL |
| Learning curve | Simpler | More complex |
| Performance | Slower | Faster (incremental) |
| Flexibility | Less | More |
| Android | Less common | Standard |
| Enterprise | More common | Growing |

### Recommended Stack

```
myapp/
├── pom.xml / build.gradle
├── src/
│   ├── main/java/com/example/myapp/
│   │   ├── model/          # User, Product
│   │   ├── dao/            # UserDao, ProductDao
│   │   ├── service/        # UserService, ProductService
│   │   ├── api/            # REST endpoints
│   │   └── Main.java       # Entry point
│   ├── main/resources/
│   │   └── application.properties
│   └── test/java/
│       └── UserServiceTest.java
└── target/ or build/
```

## کد مهم

`src/pom.xml` — Maven configuration برای full app.
`src/build.gradle` — Gradle configuration برای full app.
`src/User.java`, `src/UserService.java`, `src/UserDao.java` — application code.

## جمع‌بندی فاز ۷ — Databases/JDBC + Networking + Build Tools

| زیرشاخه | جلسات | موضوع |
|---|---|---|
| `10-databases-jdbc` | ۹۶–۱۰۲ | JDBC, PreparedStatement, Connection Pool, Transactions, JPA, Hibernate, Capstone |
| `11-networking` | ۱۰۳–۱۰۷ | Sockets, HTTP Client, WebSocket, Serialization, Capstone |
| `13-build-tools` | ۱۰۸–۱۱۰ | Maven, Gradle, Build Capstone |

**فاز ۷ کامل شد — ۱۵ جلسه ✅**
