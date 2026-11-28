# پاسخ تمرین‌های جلسه ۱۴۸

## آسان

**۱ و ۲.**
```java
public class Employee {
    private Long id;
    private String name;
    private String department;
    private double salary;
}

public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    public List<Employee> getAllEmployees() { return new ArrayList<>(employees); }
    public Employee createEmployee(String name, String dept, double salary) { /* ... */ }
}
```

## متوسط

**۳ و ۴.**
```java
public class MainFrame extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public MainFrame() {
        tableModel = new DefaultTableModel(columns, 0);
        employeeTable = new JTable(tableModel);
        add(new JScrollPane(employeeTable));
    }
}

public class EmployeeDialog extends JDialog {
    private JTextField nameField;
    private JTextField departmentField;
    private JTextField salaryField;
}
```

## سخت

**۵ و ۶.**
```java
public List<Employee> searchEmployees(String query) {
    return employees.stream()
        .filter(e -> e.getName().toLowerCase().contains(query.toLowerCase()))
        .toList();
}

public class EmployeeDAO {
    private final List<Employee> employees = new ArrayList<>();
    public void save(Employee employee) { /* save to file/db */ }
}
```
