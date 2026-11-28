// FullUserController.java — پاسخ کامل پروژهٔ جلسه ۱۱۵
// نیازمند: spring-webmvc + spring-context jars برای کامپایل
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/users")
public class FullUserController {

    private final UserService userService;

    public FullUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUserById(id).orElse(null));
        return "user-form";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user) {
        if (user.getId() == 0) {
            userService.createUser(user.getName(), user.getEmail(), user.getAge());
        } else {
            userService.updateUser(user.getId(), user.getName(), user.getEmail(), user.getAge());
        }
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
