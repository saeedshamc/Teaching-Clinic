# پاسخ تمرین‌های جلسه ۱۰۰

## آسان

**۱ و ۲.**
```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
```

## متوسط

**۳ و ۴.**
```java
EntityManager em = emf.createEntityManager();
EntityTransaction tx = em.getTransaction();

tx.begin();
em.persist(new User("Ali", "ali@mail.com"));  // INSERT
tx.commit();

tx.begin();
User user = em.find(User.class, 1L);  // SELECT
tx.commit();
```

## سخت

**۵ و ۶.**
```java
List<User> users = em.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
    .setParameter("name", "Ali")
    .getResultList();

user.setName("Updated");
em.merge(user);  // UPDATE
em.remove(user);  // DELETE
```
