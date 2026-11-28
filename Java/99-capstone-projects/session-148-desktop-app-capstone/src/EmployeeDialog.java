// EmployeeDialog.java — Add/Edit employee dialog
// نیازمند: javax.swing برای کامپایل
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

public class EmployeeDialog extends JDialog {

    private final EmployeeService employeeService;
    private final Employee employee;

    private JTextField nameField;
    private JTextField departmentField;
    private JTextField salaryField;
    private JButton saveButton;
    private JButton cancelButton;

    public EmployeeDialog(JFrame parent, Employee employee, EmployeeService employeeService) {
        super(parent, employee == null ? "Add Employee" : "Edit Employee", true);
        this.employee = employee;
        this.employeeService = employeeService;

        initializeUI();
        loadEmployeeData();
    }

    private void initializeUI() {
        setSize(400, 300);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name field
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        nameField = new JTextField(20);
        mainPanel.add(nameField, gbc);

        // Department field
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        mainPanel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        departmentField = new JTextField(20);
        mainPanel.add(departmentField, gbc);

        // Salary field
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        mainPanel.add(new JLabel("Salary:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        salaryField = new JTextField(20);
        mainPanel.add(salaryField, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> saveEmployee());
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
    }

    private void loadEmployeeData() {
        if (employee != null) {
            nameField.setText(employee.getName());
            departmentField.setText(employee.getDepartment());
            salaryField.setText(String.valueOf(employee.getSalary()));
        }
    }

    private void saveEmployee() {
        String name = nameField.getText().trim();
        String department = departmentField.getText().trim();
        String salaryText = salaryField.getText().trim();

        if (name.isEmpty() || department.isEmpty() || salaryText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double salary = Double.parseDouble(salaryText);
            if (salary < 0) {
                JOptionPane.showMessageDialog(this, "Salary must be positive", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (employee == null) {
                employeeService.createEmployee(name, department, salary);
            } else {
                employeeService.updateEmployee(employee.getId(), name, department, salary);
            }

            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid salary format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
