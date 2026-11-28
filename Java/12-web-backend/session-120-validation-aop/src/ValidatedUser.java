// ValidatedUser.java — model با validation annotations
// نیازمند: jakarta.validation-api jar برای کامپایل
import jakarta.validation.constraints.*;

public class ValidatedUser {

    @NotNull(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 chars")
    private String name;

    @Email(message = "Invalid email format")
    @NotNull(message = "Email is required")
    private String email;

    @Min(value = 18, message = "Age must be 18 or older")
    @Max(value = 120, message = "Age must be 120 or less")
    private int age;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    private String phone;

    public ValidatedUser() {}
    public ValidatedUser(String name, String email, int age, String phone) {
        this.name = name; this.email = email; this.age = age; this.phone = phone;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
