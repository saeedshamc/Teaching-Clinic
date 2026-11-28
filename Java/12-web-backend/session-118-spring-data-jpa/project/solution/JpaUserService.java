// JpaUserService.java — پاسخ کامل پروژهٔ جلسه ۱۱۸
// نیازمند: spring-context jar برای کامپایل
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUserService {

    private final JpaUserRepository userRepository;

    public JpaUserService(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() { return userRepository.findAll(); }
    public Optional<User> getUserById(Long id) { return userRepository.findById(id); }
    public User createUser(User user) { return userRepository.save(user); }
    public void deleteUser(Long id) { userRepository.deleteById(id); }

    public List<User> findByName(String name) { return userRepository.findByName(name); }
    public List<User> findAdults(int minAge) { return userRepository.findByAgeGreaterThan(minAge); }
    public Optional<User> findByEmail(String email) { return userRepository.findByEmail(email); }
    public long countAdults(int minAge) { return userRepository.countByAgeGreaterThan(minAge); }
    public boolean emailExists(String email) { return userRepository.existsByEmail(email); }
}
