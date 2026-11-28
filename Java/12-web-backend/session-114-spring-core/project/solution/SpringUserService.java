// SpringUserService.java — پاسخ کامل پروژهٔ جلسه ۱۱۴
// نیازمند: spring-context jar برای کامپایل
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@Service
public class SpringUserService {

    private final UserRepository userRepository;

    @Autowired
    public SpringUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() { return userRepository.findAll(); }
    public Optional<User> getUserById(int id) { return userRepository.findById(id); }
    public User createUser(String name, String email, int age) {
        return userRepository.save(new User(0, name, email, age));
    }
    public boolean deleteUser(int id) { return userRepository.delete(id); }
    public int getUserCount() { return userRepository.count(); }
}
