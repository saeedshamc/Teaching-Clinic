// UserController.java — Spring MVC controller
// نیازمند: spring-webmvc + spring-context jars برای کامپایل
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";   // → users.html
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user.getName(), user.getEmail(), user.getAge());
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUserById(id).orElse(null));
        return "user-form";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
