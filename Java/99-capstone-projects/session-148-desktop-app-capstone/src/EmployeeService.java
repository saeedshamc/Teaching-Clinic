// EmployeeService.java — Service layer برای desktop capstone
// قابل کامپایل با javac — بدون dependency
import java.util.*;

public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();
    private long nextId = 1;

    public EmployeeService() {
        // Sample data
        employees.add(new Employee(nextId++, "Ali Rezaei", "Engineering", 5000.0));
        employees.add(new Employee(nextId++, "Sara Ahmadi", "Marketing", 4500.0));
        employees.add(new Employee(nextId++, "Reza Mohammadi", "HR", 4000.0));
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employees.stream()
            .filter(e -> e.getId().equals(id))
            .findFirst();
    }

    public Employee createEmployee(String name, String department, double salary) {
        Employee employee = new Employee(nextId++, name, department, salary);
        employees.add(employee);
        return employee;
    }

    public Employee updateEmployee(Long id, String name, String department, double salary) {
        Employee employee = employees.stream()
            .filter(e -> e.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setName(name);
        employee.setDepartment(department);
        employee.setSalary(salary);
        return employee;
    }

    public void deleteEmployee(Long id) {
        employees.removeIf(e -> e.getId().equals(id));
    }

    public List<Employee> searchEmployees(String query) {
        return employees.stream()
            .filter(e -> e.getName().toLowerCase().contains(query.toLowerCase()) ||
                        e.getDepartment().toLowerCase().contains(query.toLowerCase()))
            .toList();
    }
}
