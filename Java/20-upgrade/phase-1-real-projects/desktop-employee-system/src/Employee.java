// Employee.java — مدل دادهٔ کارمند
// قابل کامپایل با javac — بدون dependency خارجی
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String department;
    private double salary;
    private String hireDate;   // yyyy-MM-dd
    private boolean active = true;

    public Employee() {}

    public Employee(Long id, String firstName, String lastName, String email,
                    String phone, String department, double salary, String hireDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getFullName() { return firstName + " " + lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public String getHireDate() { return hireDate; }
    public void setHireDate(String hireDate) { this.hireDate = hireDate; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // تبدیل به آرایه برای نمایش در جدول و ذخیره در فایل
    public String[] toRow() {
        return new String[]{
            String.valueOf(id), firstName, lastName, email, phone,
            department, String.valueOf(salary), hireDate, String.valueOf(active)
        };
    }

    // ساخت از آرایه خوانده‌شده از فایل
    public static Employee fromRow(String[] row) {
        Employee e = new Employee();
        e.id = Long.parseLong(row[0]);
        e.firstName = row[1];
        e.lastName = row[2];
        e.email = row[3];
        e.phone = row[4];
        e.department = row[5];
        e.salary = Double.parseDouble(row[6]);
        e.hireDate = row[7];
        e.active = Boolean.parseBoolean(row[8]);
        return e;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s) — %s — %.2f", firstName, lastName, id, department, salary);
    }
}
