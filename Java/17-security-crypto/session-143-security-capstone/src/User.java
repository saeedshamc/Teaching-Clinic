// User.java — User model برای security capstone
// قابل کامپایل با javac — بدون dependency
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;   // hashed
    private String role = "USER";

    public User() {}
    public User(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return String.format("User{id=%d, username='%s', email='%s', role='%s'}",
            id, username, email, role);
    }
}
