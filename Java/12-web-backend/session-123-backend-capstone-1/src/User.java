// User.java — JPA entity برای capstone
// نیازمند: jakarta.persistence-api jar برای کامپایل
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 chars")
    @Column(nullable = false)
    private String name;

    @Email(message = "Invalid email")
    @NotNull(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;

    @Min(value = 18, message = "Age must be 18+")
    @Max(value = 120)
    private int age;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt = java.time.LocalDateTime.now();

    @Column(name = "is_active")
    private boolean active = true;

    public User() {}
    public User(String name, String email, int age) {
        this.name = name; this.email = email; this.age = age;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public java.time.LocalDateTime getCreatedAt() { return createdAt; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
