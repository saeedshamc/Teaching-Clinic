// User.java — JPA entity برای Spring Data JPA
// نیازمند: jakarta.persistence-api + spring-data-jpa jars برای کامپایل
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private int age;

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

    @Override
    public String toString() {
        return String.format("User{id=%d, name='%s', email='%s', age=%d}", id, name, email, age);
    }
}
