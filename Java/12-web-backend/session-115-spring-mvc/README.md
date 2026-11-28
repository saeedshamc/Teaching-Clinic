# جلسه ۱۱۵ — Spring MVC: Web Layer

## اهداف یادگیری
- `DispatcherServlet` را به‌عنوان front controller بشناسید
- `@Controller`, `@RequestMapping`, `@GetMapping`, `@PostMapping` را بفهمید
- `Model`, `ModelAndView`, `@ModelAttribute` را به‌کار ببرید
- `ViewResolver` و Thymeleaf برای templates را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۱۱۴ (Spring Core)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Spring MVC Architecture

```
Request → DispatcherServlet → HandlerMapping → Controller → Model → ViewResolver → View (HTML)
```

### @Controller + @RequestMapping

```java
@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";   // → users.html via Thymeleaf
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
```

### Model — data for view

```java
@GetMapping("/{id}")
public String getUser(@PathVariable int id, Model model) {
    User user = userService.getUserById(id).orElseThrow();
    model.addAttribute("user", user);
    return "user-detail";   // → user-detail.html
}
```

### Thymeleaf Template

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
    <h1>User List</h1>
    <table>
        <tr th:each="user : ${users}">
            <td th:text="${user.name}">Name</td>
            <td th:text="${user.email}">Email</td>
        </tr>
    </table>
</body>
</html>
```

### @ResponseBody — REST response

```java
@RestController   // = @Controller + @ResponseBody
@RequestMapping("/api/users")
public class UserApiController {

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();  // → JSON automatically
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        return userService.getUserById(id).orElseThrow();
    }
}
```

## کد مهم

`src/UserController.java` — MVC controller.
`src/UserApiController.java` — REST controller.
`src/templates/users.html` — Thymeleaf template.
`src/application.properties` — Spring config.

## خطاهای رایج
- `@Controller` و `@ResponseBody` را اشتباه استفاده کردن → view name vs JSON.
- `Model` را بدون `addAttribute` برگرداندن → empty view.
- `redirect:` را بدون `/` استفاده کردن → relative path.
- Thymeleaf namespace را فراموش کردن → template not found.

## جمع‌بندی
- Spring MVC برای web layer — DispatcherServlet pattern.
- `@Controller` برای views، `@RestController` برای REST.
- `Model` برای view data، `@ModelAttribute` برای form binding.
- Thymeleaf برای server-side templates.

**جلسهٔ بعد:** Spring Boot — auto-configuration.
