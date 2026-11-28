// EmployeeDAO.java — Data access layer برای desktop capstone
// قابل کامپایل با javac — بدون dependency
import java.util.*;

public class EmployeeDAO {

    private final List<Employee> employees = new ArrayList<>();
    private long nextId = 1;

    public EmployeeDAO() {
        // Load from file or database
        loadFromFile();
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }

    public Optional<Employee> getById(Long id) {
        return employees.stream()
            .filter(e -> e.getId().equals(id))
            .findFirst();
    }

    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(nextId++);
            employees.add(employee);
        } else {
            int index = employees.indexOf(employee);
            if (index >= 0) {
                employees.set(index, employee);
            }
        }
        saveToFile();
        return employee;
    }

    public void delete(Long id) {
        employees.removeIf(e -> e.getId().equals(id));
        saveToFile();
    }

    private void loadFromFile() {
        // TODO: Load from file or database
        // For now, use in-memory storage
    }

    private void saveToFile() {
        // TODO: Save to file or database
        // For now, use in-memory storage
    }
}
