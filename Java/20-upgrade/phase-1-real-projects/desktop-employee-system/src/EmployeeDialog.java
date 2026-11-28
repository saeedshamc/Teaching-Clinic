// EmployeeDialog.java — دیالوگ افزودن/ویرایش کارمند با اعتبارسنجی
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class EmployeeDialog extends JDialog {

    private final EmployeeService service;
    private final Employee existing;
    private boolean saved = false;

    private final JTextField firstName = new JTextField(15);
    private final JTextField lastName = new JTextField(15);
    private final JTextField email = new JTextField(20);
    private final JTextField phone = new JTextField(12);
    private final JTextField department = new JTextField(12);
    private final JTextField salary = new JTextField(10);
    private final JTextField hireDate = new JTextField(12);

    public EmployeeDialog(JFrame parent, Employee existing, EmployeeService service) {
        super(parent, existing == null ? "افزودن کارمند" : "ویرایش کارمند", true);
        this.existing = existing;
        this.service = service;
        buildUI();
        if (existing != null) prefill();
        setSize(420, 380);
        setLocationRelativeTo(parent);
    }

    private void buildUI() {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        addRow(form, gbc, 0, "نام:", firstName);
        addRow(form, gbc, 1, "نام خانوادگی:", lastName);
        addRow(form, gbc, 2, "ایمیل:", email);
        addRow(form, gbc, 3, "تلفن:", phone);
        addRow(form, gbc, 4, "دپارتمان:", department);
        addRow(form, gbc, 5, "حقوق:", salary);
        addRow(form, gbc, 6, "تاریخ استخدام (yyyy-MM-dd):", hireDate);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton save = new JButton("ذخیره");
        JButton cancel = new JButton("انصراف");
        save.addActionListener(e -> doSave());
        cancel.addActionListener(e -> dispose());
        getRootPane().setDefaultButton(save);   // Enter = ذخیره
        buttons.add(save); buttons.add(cancel);

        gbc.gridy = 7; gbc.gridwidth = 2;
        form.add(buttons, gbc);
        add(form);
    }

    private void addRow(JPanel form, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        form.add(new JLabel(label), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        form.add(field, gbc);
    }

    private void prefill() {
        firstName.setText(existing.getFirstName());
        lastName.setText(existing.getLastName());
        email.setText(existing.getEmail());
        phone.setText(existing.getPhone());
        department.setText(existing.getDepartment());
        salary.setText(String.valueOf(existing.getSalary()));
        hireDate.setText(existing.getHireDate());
    }

    private void doSave() {
        try {
            String date = hireDate.getText().trim();
            if (date.isEmpty()) date = LocalDate.now().toString();
            // اعتبارسنجی فرمت تاریخ
            LocalDate.parse(date);

            double sal = Double.parseDouble(salary.getText().trim());
            if (existing == null) {
                service.create(firstName.getText().trim(), lastName.getText().trim(),
                    email.getText().trim(), phone.getText().trim(),
                    department.getText().trim(), sal, date);
            } else {
                service.update(existing.getId(), firstName.getText().trim(),
                    lastName.getText().trim(), email.getText().trim(),
                    phone.getText().trim(), department.getText().trim(), sal, date);
            }
            saved = true;
            dispose();
        } catch (NumberFormatException e) {
            error("حقوق باید عدد باشد");
        } catch (java.time.format.DateTimeParseException e) {
            error("فرمت تاریخ نامعتبر — نمونه: 1403-01-15");
        } catch (IllegalArgumentException e) {
            error(e.getMessage());
        }
    }

    private void error(String msg) {
        JOptionPane.showMessageDialog(this, msg, "خطا", JOptionPane.ERROR_MESSAGE);
    }

    public boolean isSaved() { return saved; }
}
