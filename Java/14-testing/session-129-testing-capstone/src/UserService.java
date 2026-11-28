// UserService.java — service برای testing capstone
// قابل کامپایل با javac — بدون dependency
import java.util.*;

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
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (age < 18) {
            throw new IllegalArgumentException("Age must be 18+");
        }
        User user = new User(null, name, email, age);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
