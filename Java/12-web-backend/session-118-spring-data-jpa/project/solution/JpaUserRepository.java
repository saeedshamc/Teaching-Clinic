// JpaUserRepository.java — پاسخ کامل پروژهٔ جلسه ۱۱۸
// نیازمند: spring-data-jpa jar برای کامپایل
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long> {

    // Derived queries
    List<User> findByName(String name);
    List<User> findByEmail(String email);
    List<User> findByAgeGreaterThan(int age);
    List<User> findByAgeLessThan(int age);
    List<User> findByNameContaining(String name);
    List<User> findByAgeBetween(int min, int max);
    List<User> findByAgeGreaterThanOrderByNameAsc(int age);
    Optional<User> findByEmail(String email);

    // Custom queries
    @Query("SELECT u FROM User u WHERE u.age > :minAge AND u.name LIKE %:name%")
    List<User> findAdultsByName(@Param("minAge") int minAge, @Param("name") String name);

    @Query(value = "SELECT * FROM users WHERE age > ?1", nativeQuery = true)
    List<User> findAdultsNative(int minAge);

    // Count/Exists
    long countByAgeGreaterThan(int age);
    boolean existsByEmail(String email);
}
