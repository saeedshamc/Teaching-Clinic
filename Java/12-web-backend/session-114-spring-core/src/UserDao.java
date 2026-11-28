// UserDao.java — repository layer
// نیازمند: spring-context jar برای کامپایل
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class UserDao {

    private final List<User> users = new ArrayList<>();
    private int nextId = 1;

    public UserDao() {
        users.add(new User(1, "Ali", "ali@mail.com", 25));
        users.add(new User(2, "Sara", "sara@mail.com", 30));
        nextId = 3;
    }

    public List<User> findAll() { return new ArrayList<>(users); }

    public Optional<User> findById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    public User save(User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    public boolean delete(int id) {
        return users.removeIf(u -> u.getId() == id);
    }
}
