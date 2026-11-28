// UserService.java — class under test
// قابل کامپایل با javac — بدون dependency
import java.util.*;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(String name, String email, int age) {
        User user = new User(null, name, email, age);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> searchUsers(String name) {
        return userRepository.findByName(name);
    }

    public List<User> getAdults(int minAge) {
        return userRepository.findByAgeGreaterThan(minAge);
    }
}
