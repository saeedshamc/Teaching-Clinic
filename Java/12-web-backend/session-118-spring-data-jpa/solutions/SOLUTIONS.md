# پاسخ تمرین‌های جلسه ۱۱۸

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

public interface UserRepository extends JpaRepository<User, Long> { }
```

## متوسط

**۳ و ۴.**
```java
List<User> findByName(String name);
List<User> findByAgeGreaterThan(int age);

@Query("SELECT u FROM User u WHERE u.age > :minAge")
List<User> findAdults(@Param("minAge") int minAge);
```

## سخت

**۵ و ۶.**
```java
long countByAgeGreaterThan(int age);
boolean existsByEmail(String email);

Optional<User> findByEmail(String email);
```
