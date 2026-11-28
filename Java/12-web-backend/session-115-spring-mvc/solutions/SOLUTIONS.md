# پاسخ تمرین‌های جلسه ۱۱۵

## آسان

**۱ و ۲.**
```java
@Controller
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
}
```
```html
<tr th:each="user : ${users}">
    <td th:text="${user.name}">Name</td>
</tr>
```

## متوسط

**۳ و ۴.**
```java
@PostMapping
public String createUser(@ModelAttribute User user) {
    userService.createUser(user.getName(), user.getEmail(), user.getAge());
    return "redirect:/users";
}

@GetMapping("/{id}")
public String editUser(@PathVariable int id, Model model) { ... }
```

## سخت

**۵ و ۶.**
```java
@RestController
@RequestMapping("/api/users")
public class UserApiController {
    @GetMapping
    public List<User> getAll() { return userService.getAllUsers(); }
}
```
