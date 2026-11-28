# پاسخ تمرین‌های جلسه ۱۰۱

## آسان

**۱ و ۲.**
```java
SessionFactory sf = config.buildSessionFactory();
Session session = sf.openSession();
Transaction tx = session.beginTransaction();
session.persist(new User("Ali", "ali@mail.com", 25));
tx.commit();
session.close();
```

## متوسط

**۳ و ۴.**
```java
List<User> users = session.createQuery(
    "FROM User WHERE age > :minAge", User.class)
    .setParameter("minAge", 20)
    .list();

CriteriaBuilder cb = session.getCriteriaBuilder();
CriteriaQuery<User> cq = cb.createQuery(User.class);
Root<User> root = cq.from(User.class);
cq.select(root).where(cb.gt(root.get("age"), 20));
```

## سخت

**۵ و ۶.**
```java
@OneToMany(fetch = FetchType.LAZY)
private List<Order> orders;

session.update(user);
session.delete(user);
```
