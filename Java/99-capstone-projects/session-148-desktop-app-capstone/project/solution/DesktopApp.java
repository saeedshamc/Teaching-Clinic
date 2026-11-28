// DesktopApp.java — پاسخ کامل capstone: سیستم مدیریت کارکنان
// Swing + MVC + Service layer
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class DesktopApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }

    static class Employee {
        private Long id;
        private String name;
        private String department;
        private double salary;

        Employee(Long id, String name, String department, double salary) {
            this.id = id; this.name = name; this.department = department; this.salary = salary;
        }
        Long getId() { return id; }
        String getName() { return name; }
        String getDepartment() { return department; }
        double getSalary() { return salary; }
    }

    static class EmployeeService {
        private final List<Employee> employees = new ArrayList<>();
        private long nextId = 1;

        EmployeeService() {
            employees.add(new Employee(nextId++, "Ali Rezaei", "Engineering", 5000.0));
            employees.add(new Employee(nextId++, "Sara Ahmadi", "Marketing", 4500.0));
        }

        List<Employee> getAllEmployees() { return new ArrayList<>(employees); }

        Employee createEmployee(String name, String department, double salary) {
            Employee employee = new Employee(nextId++, name, department, salary);
            employees.add(employee);
            return employee;
        }

        void deleteEmployee(Long id) {
            employees.removeIf(e -> e.getId().equals(id));
        }

        List<Employee> searchEmployees(String query) {
            return employees.stream()
                .filter(e -> e.getName().toLowerCase().contains(query.toLowerCase()) ||
                            e.getDepartment().toLowerCase().contains(query.toLowerCase()))
                .toList();
        }
    }

    static class MainFrame extends JFrame {
        private final EmployeeService employeeService = new EmployeeService();
        private final DefaultTableModel tableModel;
        private final JTable employeeTable;
        private final JTextField searchField;

        MainFrame() {
            setTitle("Employee Management System");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Table
            String[] columns = {"ID", "Name", "Department", "Salary"};
            tableModel = new DefaultTableModel(columns, 0) {
                @Override public boolean isCellEditable(int row, int column) { return false; }
            };
            employeeTable = new JTable(tableModel);

            // Search panel
            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            searchField = new JTextField(20);
            JButton searchButton = new JButton("Search");
            JButton clearButton = new JButton("Clear");
            JButton addButton = new JButton("Add");
            JButton deleteButton = new JButton("Delete");
            JButton refreshButton = new JButton("Refresh");

            searchButton.addActionListener(e -> searchEmployees());
            clearButton.addActionListener(e -> { searchField.setText(""); loadEmployees(); });
            addButton.addActionListener(e -> showAddDialog());
            deleteButton.addActionListener(e -> deleteSelectedEmployee());
            refreshButton.addActionListener(e -> loadEmployees());

            searchPanel.add(new JLabel("Search: "));
            searchPanel.add(searchField);
            searchPanel.add(searchButton);
            searchPanel.add(clearButton);
            searchPanel.add(addButton);
            searchPanel.add(deleteButton);
            searchPanel.add(refreshButton);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(searchPanel, BorderLayout.NORTH);
            mainPanel.add(new JScrollPane(employeeTable), BorderLayout.CENTER);
            add(mainPanel);

            loadEmployees();
        }

        private void loadEmployees() {
            tableModel.setRowCount(0);
            for (Employee employee : employeeService.getAllEmployees()) {
                tableModel.addRow(new Object[]{
                    employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary()
                });
            }
        }

        private void searchEmployees() {
            String query = searchField.getText().trim();
            if (query.isEmpty()) { loadEmployees(); return; }
            tableModel.setRowCount(0);
            for (Employee employee : employeeService.searchEmployees(query)) {
                tableModel.addRow(new Object[]{
                    employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary()
                });
            }
        }

        private void showAddDialog() {
            String name = JOptionPane.showInputDialog(this, "Name:");
            String department = JOptionPane.showInputDialog(this, "Department:");
            String salaryText = JOptionPane.showInputDialog(this, "Salary:");
            if (name != null && department != null && salaryText != null) {
                try {
                    double salary = Double.parseDouble(salaryText);
                    employeeService.createEmployee(name, department, salary);
                    loadEmployees();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid salary", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        private void deleteSelectedEmployee() {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Please select an employee", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Long id = (Long) tableModel.getValueAt(selectedRow, 0);
            String name = (String) tableModel.getValueAt(selectedRow, 1);
            int result = JOptionPane.showConfirmDialog(this, "Delete " + name + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                employeeService.deleteEmployee(id);
                loadEmployees();
            }
        }
    }
}
