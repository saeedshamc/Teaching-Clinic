// RegistrationUser.java — model برای registration
// قابل کامپایل با javac — بدون dependency خارجی
public class RegistrationUser {
    private int id;
    private String name;
    private String email;
    private String password;

    public RegistrationUser() {}

    public RegistrationUser(int id, String name, String email, String password) {
        this.id = id; this.name = name; this.email = email; this.password = password;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return String.format("User{id=%d, name='%s', email='%s'}", id, name, email);
    }
}
