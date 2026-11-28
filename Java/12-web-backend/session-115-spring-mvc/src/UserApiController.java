// UserApiController.java — REST API controller
// نیازمند: spring-webmvc + spring-context jars برای کامپایل
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        return userService.getUserById(id).orElseThrow();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.createUser(user.getName(), user.getEmail(), user.getAge());
    }

    @PutMapping("/{id}")
    public User update(@PathVariable int id, @RequestBody User user) {
        // update logic
        return user;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
