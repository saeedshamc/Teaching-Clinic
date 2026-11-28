// UserService.java — service layer برای capstone
// نیازمند: spring-context jar برای کامپایل
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userData) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new RuntimeException("User not found: " + id));
        user.setName(userData.getName());
        user.setEmail(userData.getEmail());
        user.setAge(userData.getAge());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> searchUsers(String name) {
        return userRepository.findByNameContaining(name);
    }

    public List<User> getActiveAdults(int minAge) {
        return userRepository.findActiveAdults(minAge);
    }

    public long countActiveUsers() {
        return userRepository.countByActiveTrue();
    }
}
