# جلسه ۱۰۱ — Hibernate: JPA Implementation

## اهداف یادگیری
- `Hibernate` را به‌عنوان JPA implementation پرکاربرد بشناسید
- `SessionFactory`, `Session`, `Transaction` را بفهمید
- `Criteria API`, `HQL` برای queries را به‌کار ببرید
- `Lazy` vs `Eager` loading را تشخیص دهید

## پیش‌نیازها
- جلسهٔ ۱۰۰ (JPA Intro)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Hibernate چیست؟

`Hibernate` — محبوب‌ترین JPA implementation. `EntityManager` را به `Session` map می‌کند:

```java
// JPA
EntityManager em = emf.createEntityManager();

// Hibernate — همان چیز با نام‌های Hibernate
Session session = sessionFactory.openSession();
// session ≈ EntityManager
// sessionFactory ≈ EntityManagerFactory
```

### Setup

```gradle
dependencies {
    implementation 'org.hibernate.orm:hibernate-core:6.4.0.Final'
    implementation 'com.h2database:h2:2.2.224'   // یا هر دیتابیس
}
```

### Hibernate Native API

```java
Configuration config = new Configuration()
    .addAnnotatedClass(User.class)
    .setProperty("hibernate.connection.url", "jdbc:h2:mem:test")
    .setProperty("hibernate.hbm2ddl.auto", "create-drop");

SessionFactory sessionFactory = config.buildSessionFactory();

// CRUD
Session session = sessionFactory.openSession();
Transaction tx = session.beginTransaction();

User user = new User("Ali", "ali@mail.com");
session.persist(user);   // INSERT
tx.commit();

User found = session.get(User.class, user.getId());  // SELECT
tx = session.beginTransaction();
found.setName("Updated");
session.update(found);   // UPDATE
tx.commit();

session.delete(found);   // DELETE
tx.commit();
session.close();
```

### HQL — Hibernate Query Language

```java
// شبیه JPQL ولی Hibernate-specific
List<User> users = session.createQuery(
    "FROM User WHERE age > :minAge", User.class)
    .setParameter("minAge", 20)
    .list();

// Criteria API — type-safe
CriteriaBuilder cb = session.getCriteriaBuilder();
CriteriaQuery<User> cq = cb.createQuery(User.class);
Root<User> root = cq.from(User.class);
cq.select(root).where(cb.gt(root.get("age"), 20));
List<User> result = session.createQuery(cq).list();
```

### Lazy vs Eager Loading

```java
@Entity
public class User {
    @OneToMany(fetch = FetchType.LAZY)   // پیش‌فرض برای collections
    private List<Order> orders;

    @ManyToOne(fetch = FetchType.EAGER)  // پیش‌فرض برای many-to-one
    private Department department;
}

// Lazy — orders فقط وقتی access شود load می‌شود
// Eager — orders همراه user load می‌شود
// در session باز باید access شود — session.close() قبل → LazyInitializationException
```

## کد مهم

`src/HibernateDemo.java` — CRUD با Hibernate native API.
`src/User.java` — entity با relationships.

## خطاهای رایج
- `session.close()` قبل از access lazy collection → `LazyInitializationException`.
- `tx.commit()` را فراموش کردن → transaction rollback.
- `sessionFactory` را بدون `close` رها کردن → resource leak.
- `hbm2ddl.auto` را در production با `create-drop` استفاده کردن → data loss.

## جمع‌بندی
- Hibernate = JPA implementation — `Session` ≈ `EntityManager`.
- `SessionFactory` thread-safe، `Session` not thread-safe.
- HQL و Criteria API برای queries.
- Lazy loading برای performance، Eager برای simplicity.

**جلسهٔ بعد:** JDBC Capstone.
