// MainFrame.java — Main window برای desktop capstone
// نیازمند: javax.swing برای کامپایل
// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.awt.event.*;
// import java.util.*;

public class MainFrame extends JFrame {

    private final EmployeeService employeeService;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public MainFrame() {
        this.employeeService = new EmployeeService();
        initializeUI();
        loadEmployees();
    }

    private void initializeUI() {
        setTitle("Employee Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton clearButton = new JButton("Clear");
        searchButton.addActionListener(e -> searchEmployees());
        clearButton.addActionListener(e -> { searchField.setText(""); loadEmployees(); });
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);

        // Table
        String[] columns = {"ID", "Name", "Department", "Salary"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        employeeTable = new JTable(tableModel);
        employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Employee");
        JButton editButton = new JButton("Edit Employee");
        JButton deleteButton = new JButton("Delete Employee");
        JButton refreshButton = new JButton("Refresh");

        addButton.addActionListener(e -> showEmployeeDialog(null));
        editButton.addActionListener(e -> editSelectedEmployee());
        deleteButton.addActionListener(e -> deleteSelectedEmployee());
        refreshButton.addActionListener(e -> loadEmployees());

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        // Layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(employeeTable), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void loadEmployees() {
        tableModel.setRowCount(0);
        for (Employee employee : employeeService.getAllEmployees()) {
            tableModel.addRow(new Object[]{
                employee.getId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getSalary()
            });
        }
    }

    private void searchEmployees() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            loadEmployees();
            return;
        }

        tableModel.setRowCount(0);
        for (Employee employee : employeeService.searchEmployees(query)) {
            tableModel.addRow(new Object[]{
                employee.getId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getSalary()
            });
        }
    }

    private void showEmployeeDialog(Employee employee) {
        EmployeeDialog dialog = new EmployeeDialog(this, employee, employeeService);
        dialog.setVisible(true);
        loadEmployees();   // refresh table
    }

    private void editSelectedEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select an employee", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Long id = (Long) tableModel.getValueAt(selectedRow, 0);
        Employee employee = employeeService.getEmployeeById(id).orElse(null);
        if (employee != null) {
            showEmployeeDialog(employee);
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

        int result = JOptionPane.showConfirmDialog(this,
            "Delete employee: " + name + "?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            employeeService.deleteEmployee(id);
            loadEmployees();
        }
    }
}
