// EmployeeService.java — منطق برنامه: CRUD + جستجو + اعتبارسنجی
// قابل کامپایل با javac — بدون dependency خارجی
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {

    private final EmployeeDAO dao = new EmployeeDAO();
    private final List<Employee> employees;   // حافظهٔ کاری
    private long nextId;

    public EmployeeService() {
        this.employees = dao.loadAll();
        this.nextId = employees.stream()
            .mapToLong(Employee::getId)
            .max().orElse(0) + 1;
    }

    // ---------- CRUD ----------
    public List<Employee> getAll() {
        return employees.stream()
            .filter(Employee::isActive)
            .collect(Collectors.toList());
    }

    public Employee create(String firstName, String lastName, String email,
                           String phone, String department, double salary, String hireDate) {
        validate(firstName, lastName, email, salary);
        Employee e = new Employee(nextId++, firstName, lastName, email, phone,
                                  department, salary, hireDate);
        employees.add(e);
        dao.saveAll(employees);
        return e;
    }

    public Employee update(Long id, String firstName, String lastName, String email,
                           String phone, String department, double salary, String hireDate) {
        Employee e = findById(id)
            .orElseThrow(() -> new IllegalArgumentException("کارمند پیدا نشد: " + id));
        validate(firstName, lastName, email, salary);
        e.setFirstName(firstName); e.setLastName(lastName); e.setEmail(email);
        e.setPhone(phone); e.setDepartment(department); e.setSalary(salary);
        e.setHireDate(hireDate);
        dao.saveAll(employees);
        return e;
    }

    public void delete(Long id) {
        Employee e = findById(id)
            .orElseThrow(() -> new IllegalArgumentException("کارمند پیدا نشد: " + id));
        e.setActive(false);   // حذف نرم — رکورد باقی می‌ماند
        dao.saveAll(employees);
    }

    // ---------- جستجو و فیلتر ----------
    public List<Employee> search(String query) {
        String q = query.toLowerCase();
        return employees.stream()
            .filter(Employee::isActive)
            .filter(e -> e.getFullName().toLowerCase().contains(q)
                      || e.getEmail().toLowerCase().contains(q)
                      || e.getDepartment().toLowerCase().contains(q))
            .collect(Collectors.toList());
    }

    public List<Employee> filterByDepartment(String department) {
        return employees.stream()
            .filter(Employee::isActive)
            .filter(e -> e.getDepartment().equalsIgnoreCase(department))
            .collect(Collectors.toList());
    }

    // ---------- آمار ----------
    public double averageSalary() {
        return employees.stream()
            .filter(Employee::isActive)
            .mapToDouble(Employee::getSalary)
            .average().orElse(0.0);
    }

    public Map<String, Integer> countByDepartment() {
        return dao.countByDepartment(employees);
    }

    public long totalCount() {
        return employees.stream().filter(Employee::isActive).count();
    }

    // ---------- داخلی ----------
    private Optional<Employee> findById(Long id) {
        return employees.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    private void validate(String firstName, String lastName, String email, double salary) {
        if (firstName == null || firstName.isBlank())
            throw new IllegalArgumentException("نام الزامی است");
        if (lastName == null || lastName.isBlank())
            throw new IllegalArgumentException("نام خانوادگی الزامی است");
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("ایمیل نامعتبر است");
        if (salary < 0)
            throw new IllegalArgumentException("حقوق نمی‌تواند منفی باشد");
    }
}
