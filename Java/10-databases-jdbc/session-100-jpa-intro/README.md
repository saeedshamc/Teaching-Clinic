# جلسه ۱۰۰ — JPA Intro: ORM در جاوا

## اهداف یادگیری
- `JPA` (Jakarta Persistence API) را به‌عنوان ORM استاندارد بشناسید
- `@Entity`, `@Id`, `@Table`, `@Column` annotations را بفهمید
- `EntityManager`, `Persistence`, `EntityTransaction` را به‌کار ببرید
- تفاوت JPA و JDBC را تشخیص دهید

## پیش‌نیازها
- جلسهٔ ۹۹ (Transactions)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### JPA چیست؟

`JPA` (Java Persistence API) — استاندارد ORM (Object-Relational Mapping) در جاوا. به‌جای نوشتن SQL دستی، با **objectهای جاوا** کار می‌کنید:

```java
// JDBC — دستی
String sql = "SELECT * FROM users WHERE id = ?";
ResultSet rs = ps.executeQuery();
User user = new User(rs.getInt("id"), rs.getString("name"));

// JPA — خودکار
User user = entityManager.find(User.class, 1);
```

### Entity — mapping به table

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String name;

    @Column(unique = true)
    private String email;

    // getters/setters — الزامی
}
```

### Persistence Unit — تنظیمات در persistence.xml

```xml
<persistence>
    <persistence-unit name="myPU" transaction-type="RESOURCE_LOCAL">
        <class>com.example.User</class>
        <properties>
            <property name="jakarta.persistence.jdbc.url" value="jdbc:sqlite:test.db"/>
            <property name="jakarta.persistence.jdbc.user" value=""/>
            <property name="jakarta.persistence.jdbc.password" value=""/>
            <property name="jakarta.persistence.schema-generation.database.action" value="create"/>
        </properties>
    </persistence-unit>
</persistence>
```

### EntityManager — عملیات CRUD

```java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
EntityManager em = emf.createEntityManager();
EntityTransaction tx = em.getTransaction();

// INSERT
tx.begin();
User user = new User("Ali", "ali@mail.com");
em.persist(user);   // INSERT
tx.commit();

// SELECT
tx.begin();
User found = em.find(User.class, 1L);  // SELECT by id
List<User> all = em.createQuery("SELECT u FROM User u", User.class).getResultList();
tx.commit();

// UPDATE
user.setName("Ali Updated");
em.merge(user);  // UPDATE

// DELETE
em.remove(user);  // DELETE

em.close();
emf.close();
```

### JPQL — Java Persistence Query Language

```java
// شبیه SQL ولی با object names
tx.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
    .setParameter("name", "Ali")
    .getResultList();

// Named queries
@Entity
@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
public class User { ... }
```

## کد مهم

`src/User.java` — JPA entity.
`src/JpaDemo.java` — CRUD با EntityManager.
`src/persistence.xml` — تنظیمات.

## خطاهای رایج
- `getTransaction` را بدون `begin` استفاده کردن → `TransactionRequiredException`.
- `persist` را برای entity موجود استفاده کردن → duplicate key.
- `merge` را برای entity جدید استفاده کردن → `EntityNotFoundException`.
- getters/setters را فراموش کردن → JPA نمی‌تواند access کند.

## جمع‌بندی
- JPA = ORM استاندارد — object به‌جای SQL.
- `@Entity` + `@Id` + `@GeneratedValue` برای mapping.
- `EntityManager` برای CRUD، `EntityTransaction` برای transactions.
- JPQL برای queries.

**جلسهٔ بعد:** Hibernate — JPA implementation پرکاربرد.
