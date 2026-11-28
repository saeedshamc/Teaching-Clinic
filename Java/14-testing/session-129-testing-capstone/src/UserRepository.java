// UserRepository.java — interface برای mocking
// قابل کامپایل با javac — بدون dependency
import java.util.*;

public interface UserRepository {
    Optional<User> findById(Long id);
    List<User> findAll();
    User save(User user);
    void deleteById(Long id);
    List<User> findByName(String name);
    List<User> findByAgeGreaterThan(int age);
}
