// UserService.java — service using Spring Data JPA
// نیازمند: spring-context jar برای کامپایل
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(String name, String email, int age) {
        return userRepository.save(new User(name, email, age));
    }

    public User updateUser(Long id, String name, String email, int age) {
        User user = userRepository.findById(id).orElseThrow();
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Derived queries
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findAdults(int minAge) {
        return userRepository.findByAgeGreaterThan(minAge);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
