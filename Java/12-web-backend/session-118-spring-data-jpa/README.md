# جلسه ۱۱۸ — Spring Data JPA: Database Integration

## اهداف یادگیری
- `Spring Data JPA` را برای database access بشناسید
- `JpaRepository`, `CrudRepository` را بفهمید
- Derived query methods را به‌کار ببرید
- `@Entity`, `@Table`, `@Id` را برای JPA entities استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۱۷ (REST Controllers)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Spring Data JPA چیست؟

`Spring Data JPA` — repository abstraction برای JPA. بدون نوشتن SQL یا JPQL، با method names query می‌سازید:

```java
public interface UserRepository extends JpaRepository<User, Long> {
    // Derived queries — auto-generated
    List<User> findByName(String name);
    List<User> findByEmail(String email);
    List<User> findByAgeGreaterThan(int age);
    List<User> findByNameContaining(String name);
    Optional<User> findByEmailAndAge(String email, int age);
}
```

### JpaRepository Methods

```java
// Built-in methods
userRepository.save(user);           // INSERT/UPDATE
userRepository.findById(1L);         // SELECT by id
userRepository.findAll();            // SELECT all
userRepository.deleteById(1L);       // DELETE
userRepository.count();              // COUNT
userRepository.existsById(1L);       // EXISTS
```

### @Entity — JPA mapping

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private int age;

    // getters/setters
}
```

### Derived Query Methods

```java
// findBy + property name
List<User> findByName(String name);
List<User> findByEmail(String email);
List<User> findByAge(int age);

// findBy + multiple properties
List<User> findByNameAndEmail(String name, String email);
List<User> findByNameOrEmail(String name, String email);

// Comparison operators
List<User> findByAgeGreaterThan(int age);
List<User> findByAgeLessThan(int age);
List<User> findByAgeBetween(int min, int max);
List<User> findByNameContaining(String name);   // LIKE %name%
List<User> findByNameStartingWith(String prefix);
List<User> findByNameEndingWith(String suffix);

// Sorting
List<User> findByAgeGreaterThanOrderByNameAsc(int age);
List<User> findAllByOrderByNameDesc();

// Optional return
Optional<User> findByEmail(String email);
```

### Custom Queries — @Query

```java
@Query("SELECT u FROM User u WHERE u.age > :minAge AND u.name LIKE %:name%")
List<User> findAdultsByName(@Param("minAge") int minAge, @Param("name") String name);

@Query(value = "SELECT * FROM users WHERE age > ?1", nativeQuery = true)
List<User> findAdultsNative(int minAge);
```

## کد مهم

`src/User.java` — JPA entity.
`src/UserRepository.java` — Spring Data repository.
`src/UserService.java` — service using repository.
`src/UserController.java` — REST controller.

## خطاهای رایج
- `findBy` را بدون property name استفاده کردن → `PropertyReferenceException`.
- `@Id` را بدون `@GeneratedValue` استفاده کردن → ID not generated.
- `nativeQuery` را بدون `value` استفاده کردن → `IllegalArgumentException`.
- `Optional` را بدون `orElse` استفاده کردن → `NoSuchElementException`.

## جمع‌بندی
- Spring Data JPA برای database access — repository pattern.
- `JpaRepository` با built-in CRUD methods.
- Derived queries با method names — `findByName`, `findByAgeGreaterThan`.
- `@Query` برای custom queries.

**جلسهٔ بعد:** Spring Security — authentication & authorization.
