// UserService.java — service layer برای users
// با javac کامپایل می‌شود — فقط JDK
import java.util.*;

public class UserService {

    private final List<User> users = new ArrayList<>();
    private int nextId = 1;

    public User create(String name, String email, int age) {
        User user = new User(nextId++, name, email, age);
        users.add(user);
        return user;
    }

    public Optional<User> findById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    public boolean update(int id, String name, String email, int age) {
        Optional<User> opt = findById(id);
        if (opt.isPresent()) {
            User u = opt.get();
            // update fields
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        return users.removeIf(u -> u.getId() == id);
    }
}
