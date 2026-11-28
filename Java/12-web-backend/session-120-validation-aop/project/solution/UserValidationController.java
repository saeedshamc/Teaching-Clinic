// UserValidationController.java — پاسخ کامل پروژهٔ جلسه ۱۲۰
// نیازمند: spring-boot-starter-web + spring-boot-starter-validation jars
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserValidationController {

    private final List<ValidatedUser> users = new ArrayList<>();

    @PostMapping
    public ResponseEntity<ValidatedUser> create(@Valid @RequestBody ValidatedUser user) {
        users.add(user);
        return ResponseEntity.status(201).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ValidatedUser> update(
            @PathVariable int id, @Valid @RequestBody ValidatedUser user) {
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValidatedUser> getById(@PathVariable int id) {
        if (id < 0) throw new IllegalArgumentException("Invalid ID");
        return ResponseEntity.ok(users.get(id));
    }
}
