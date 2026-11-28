// UserService.java — service layer با DI
// نیازمند: spring-context jar برای کامپایل
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    private final UserDao userDao;

    // Constructor injection — best practice
    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userDao.findById(id);
    }

    public User createUser(String name, String email, int age) {
        return userDao.save(new User(0, name, email, age));
    }

    public boolean deleteUser(int id) {
        return userDao.delete(id);
    }
}
