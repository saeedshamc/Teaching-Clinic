# جلسه ۱۴۸ — Desktop App Capstone: پروژهٔ نهایی دسکتاپ

## اهداف یادگیری
- همهٔ مباحث Desktop Java را در یک پروژهٔ واقعی ترکیب کنید
- Swing/JavaFX + MVC + Database + Concurrency را با هم استفاده کنید
- لایه‌بندی صحیح و best practices را در نظر بگیرید

## پیش‌نیازها
- جلسات ۶۱–۷۵ (Desktop)

## مدت زمان پیشنهادی
۱۲۰ دقیقه

## درس

### پروژه: Employee Management System

یک سیستم مدیریت کارکنان با Swing بسازید:

**Features:**
- `Employee` model با `id`, `name`, `department`, `salary`
- `EmployeeService` برای business logic
- `EmployeeDAO` برای data access (JDBC یا file)
- `MainFrame` با Swing UI — JTable, forms, buttons
- `EmployeeDialog` برای add/edit employee
- `SearchPanel` برای search/filter

### Architecture

```
MainFrame (Swing UI)
    ↓
EmployeeService (business logic)
    ↓
EmployeeDAO (data access)
    ↓
Employee (model)
```

### Swing Components

```java
// MainFrame
public class MainFrame extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private EmployeeService employeeService;

    public MainFrame() {
        // Initialize UI
        // Load employees
        // Add event listeners
    }
}

// EmployeeDialog
public class EmployeeDialog extends JDialog {
    private JTextField nameField;
    private JTextField departmentField;
    private JTextField salaryField;

    public EmployeeDialog(JFrame parent, Employee employee) {
        // Initialize form
        // Load employee data
        // Save on OK
    }
}
```

## کد مهم

`src/Employee.java` — model.
`src/EmployeeService.java` — service layer.
`src/EmployeeDAO.java` — data access.
`src/MainFrame.java` — main window.
`src/EmployeeDialog.java` — add/edit dialog.

## جمع‌بندی
- Swing برای desktop UI — JTable, forms, dialogs.
- MVC pattern — Model, View, Controller.
- JDBC یا file برای data persistence.
- Event handling برای user interactions.

**جلسهٔ بعد:** Android App Capstone.
