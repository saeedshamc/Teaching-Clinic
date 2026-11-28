// ValidatedUserController.java — controller با validation
// نیازمند: spring-boot-starter-web + spring-boot-starter-validation jars
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class ValidatedUserController {

    private final List<ValidatedUser> users = new ArrayList<>();

    @PostMapping
    public ResponseEntity<ValidatedUser> create(@Valid @RequestBody ValidatedUser user) {
        // @Valid triggers validation — throws MethodArgumentNotValidException if invalid
        users.add(user);
        return ResponseEntity.status(201).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ValidatedUser> update(
            @PathVariable int id, @Valid @RequestBody ValidatedUser user) {
        // update logic
        return ResponseEntity.ok(user);
    }
}
