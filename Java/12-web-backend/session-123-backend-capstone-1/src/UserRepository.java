// UserRepository.java — Spring Data JPA repository
// نیازمند: spring-data-jpa jar برای کامپایل
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContaining(String name);
    Optional<User> findByEmail(String email);
    List<User> findByAgeGreaterThan(int age);
    List<User> findByActiveTrue();
    long countByActiveTrue();

    @Query("SELECT u FROM User u WHERE u.age > :minAge AND u.active = true")
    List<User> findActiveAdults(@Param("minAge") int minAge);
}
