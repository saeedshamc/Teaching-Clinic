# Cheat Sheets — نکات طلایی هر بخش دورهٔ جاوا

## ۰۱ — Core Fundamentals
- `var` برای type inference محلی — نه برای fieldها
- `String` immutable است — concat در حلقه از `StringBuilder` استفاده کن
- `==` برای object مقایسهٔ reference است — `.equals()` برای مقدار
- `switch` روی String/int/enum کار می‌کند (Java 7+)
- `try-with-resources` همیشه برای Closeableها

## ۰۲ — OOP
- Encapsulation: private fields + getter/setter
- `super()` باید اولین خط constructor باشد
- `@Override` همیشه بگذار تا کامپایلر اشتباه را بگیرد
- Interface = contract؛ Abstract class = partial impl
- enum برای ثابت‌های ثابت — می‌تواند فیلد و متد داشته باشد

## ۰۳ — Collections
- `ArrayList` برای دسترسی تصادفی، `LinkedList` برای insert/delete مکرر
- `HashMap` O(1) — key باید `hashCode`/`equals` درست داشته باشد
- `HashSet` برای unique — `LinkedHashSet` ترتیب درج را حفظ می‌کند
- `ConcurrentHashMap` برای multi-thread
- `Collections.unmodifiable*` برای لیست read-only

## ۰۴ — Exceptions/IO
- Checked vs Unchecked — checked باید catch یا throws شود
- `try-with-resources` برای AutoCloseable
- `Path`/`Files` (NIO.2) بهتر از `File` قدیمی
- `BufferedReader`/`BufferedWriter` برای فایل متنی بزرگ
- Serialization فقط برای simple data — نه برای entity پیچیده

## ۰۵ — Concurrency
- `Runnable`/`Callable` برای task — `ExecutorService` برای pool
- `synchronized` برای mutual exclusion — `ReentrantLock` برای flexibility
- `CompletableFuture` برای async composition
- `volatile` برای visibility — `Atomic*` برای lock-free
- Virtual Threads (Java 21) برای I/O-bound concurrency

## ۰۶ — Modern Java
- Lambda برای functional interfaces — method reference `::`
- Stream: `map/filter/reduce/collect` — lazy evaluation
- `Optional` به‌جای null — `orElse/orElseThrow`
- `record` برای immutable data — `sealed` برای restricted hierarchy
- `var`، Text Blocks `"""..."""`، Pattern Matching `instanceof`

## ۰۷ — Legacy
- `Vector`/`Hashtable` synchronized هستند — `ArrayList`/`HashMap` بهتر
- `Date`/`Calendar` قدیمی — `java.time.*` (LocalDate, LocalDateTime)
- `Applet` مرده — Java Web Start هم deprecated
- `RMI` قدیمی — gRPC/REST مدرن‌تر

## ۰۸ — Desktop
- Swing: `JFrame` + `JPanel` + `BorderLayout`/`GridBagLayout`
- `Event Dispatch Thread` — UI فقط در EDT
- `DefaultTableModel` برای JTable — `TableModel` سفارشی برای complex
- JavaFX: FXML + controller یا pure Java — `Platform.runLater`
- `SwingWorker` برای background task در Swing

## ۰۹ — Android
- Activity lifecycle: onCreate → onStart → onResume → onPause → onStop → onDestroy
- Intent برای navigation — explicit/implicit
- RecyclerView + Adapter برای لیست‌ها
- ViewModel + LiveData/StateFlow برای UI state
- Room برای local DB — Retrofit برای network — Coroutines برای async

## ۱۰ — Databases/JDBC
- `DriverManager.getConnection` برای JDBC ساده
- `PreparedStatement` برای SQL injection-safe
- Connection Pool (HikariCP) برای production
- JPA: `@Entity` + `@Repository` — Hibernate زیرش
- Transaction: `@Transactional` — rollback on RuntimeException

## ۱۱ — Networking
- `Socket`/`ServerSocket` برای TCP — `DatagramSocket` برای UDP
- `HttpClient` (Java 11+) برای HTTP — `WebSocket` برای real-time
- `Serialization` روی socket برای object transfer
- REST: stateless — JWT برای auth

## ۱۲ — Web Backend
- Servlet lifecycle: init → service → destroy
- JSP برای view — JSTL برای logic
- Spring: `@Component`/`@Service`/`@Repository`/`@Controller`
- Spring Boot: auto-config — `application.properties`/`yml`
- REST: `@RestController` + `@RequestMapping` + DTO
- Security: `SecurityFilterChain` + JWT filter

## ۱۳ — Build Tools
- Maven: `pom.xml` — `mvn clean install` — lifecycle phases
- Gradle: `build.gradle` — `gradle build` — DSL انعطاف‌پذیرتر
- Dependency management — version conflict resolution
- Multi-module projects

## ۱۴ — Testing
- JUnit 5: `@Test` + assertions — `@BeforeEach`/`@AfterEach`
- Mockito: `mock()` + `when().thenReturn()` + `verify()`
- TDD: Red → Green → Refactor
- Integration tests: `@SpringBootTest` + `TestRestTemplate`

## ۱۵ — Design Patterns
- Creational: Singleton, Factory, Builder, Prototype
- Structural: Adapter, Decorator, Facade, Composite
- Behavioral: Observer, Strategy, Command, Chain of Responsibility
- اصل: composition over inheritance — program to interface

## ۱۶ — JVM Internals
- Heap: Young (Eden/Survivor) + Old — Stack برای call frames
- GC: G1 (default)، ZGC برای low-latency — `-Xms`/`-Xmx` tuning
- ClassLoader: Bootstrap → Platform → App — delegation model
- Profiling: JFR، VisualVM، async-profiler

## ۱۷ — Security/Crypto
- Hashing (SHA-256) ≠ Encryption (AES/RSA) — password = hash + salt
- BCrypt/PBKDF2 برای password — هرگز plain text ذخیره نکن
- JWT: Header.Payload.Signature — Bearer token
- AES برای symmetric — RSA برای asymmetric — HTTPS برای transport

## ۱۸ — DevOps
- Docker: multi-stage build — `docker build`/`run`/`compose`
- CI/CD: GitHub Actions/Jenkins — build → test → deploy
- Message queues: RabbitMQ/Kafka برای async
- Microservices: service decomposition + API Gateway + service discovery

## ۹۹ — Capstones
- Desktop: Swing + file persistence + MVC
- Android: Compose + Room + ViewModel + Navigation
- Backend: Spring Boot + JPA + Security + WebSocket
