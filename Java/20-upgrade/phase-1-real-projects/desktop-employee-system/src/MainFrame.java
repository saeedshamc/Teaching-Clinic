// MainFrame.java — پنجرهٔ اصلی برنامه با JTable و ابزارها
// نیازمند: javax.swing (بخشی از JDK)
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {

    private static final String[] COLUMNS =
        {"ID", "نام", "نام خانوادگی", "ایمیل", "تلفن", "دپارتمان", "حقوق", "تاریخ استخدام", "فعال"};

    private final EmployeeService service = new EmployeeService();
    private final DefaultTableModel model;
    private final JTable table;
    private final JTextField searchField;
    private final JComboBox<String> deptFilter;
    private final JLabel statsLabel;

    public MainFrame() {
        super("سیستم مدیریت کارکنان");

        // ---------- مدل جدول ----------
        model = new DefaultTableModel(COLUMNS, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(24);

        // ---------- نوار ابزار بالا ----------
        searchField = new JTextField(18);
        JButton searchBtn = new JButton("جستجو");
        JButton refreshBtn = new JButton("بروزرسانی");
        JButton addBtn = new JButton("➕ افزودن");
        JButton editBtn = new JButton("✏️ ویرایش");
        JButton deleteBtn = new JButton("🗑 حذف");
        JButton exportBtn = new JButton("📄 خروجی CSV");
        JButton statsBtn = new JButton("📊 آمار");

        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        top.add(new JLabel("جستجو:"));
        top.add(searchField);
        top.add(searchBtn);
        top.add(refreshBtn);
        top.add(Box.createHorizontalStrut(20));
        top.add(addBtn); top.add(editBtn); top.add(deleteBtn);
        top.add(exportBtn); top.add(statsBtn);

        // ---------- فیلتر دپارتمان ----------
        deptFilter = new JComboBox<>();
        deptFilter.addItem("همهٔ دپارتمان‌ها");
        for (String d : service.countByDepartment().keySet()) deptFilter.addItem(d);
        deptFilter.addActionListener(e -> applyFilter());

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        filterPanel.add(new JLabel("دپارتمان:"));
        filterPanel.add(deptFilter);

        // ---------- برچسب آمار ----------
        statsLabel = new JLabel();
        statsLabel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        // ---------- چیدمان ----------
        JPanel main = new JPanel(new BorderLayout(5, 5));
        main.add(top, BorderLayout.NORTH);
        main.add(filterPanel, BorderLayout.NORTH);
        main.add(new JScrollPane(table), BorderLayout.CENTER);
        main.add(statsLabel, BorderLayout.SOUTH);
        add(main);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // ---------- رویدادها ----------
        searchBtn.addActionListener(e -> applySearch());
        refreshBtn.addActionListener(e -> reload());
        addBtn.addActionListener(e -> showDialog(null));
        editBtn.addActionListener(e -> editSelected());
        deleteBtn.addActionListener(e -> deleteSelected());
        exportBtn.addActionListener(e -> exportCsv());
        statsBtn.addActionListener(e -> showStats());
        searchField.addActionListener(e -> applySearch()); // Enter هم جستجو کند

        reload();
    }

    // ---------- منطق نمایش ----------
    private void reload() {
        fillTable(service.getAll());
        updateStatsLabel();
    }

    private void applySearch() {
        String q = searchField.getText().trim();
        fillTable(q.isEmpty() ? service.getAll() : service.search(q));
    }

    private void applyFilter() {
        String dept = (String) deptFilter.getSelectedItem();
        fillTable("همهٔ دپارتمان‌ها".equals(dept)
            ? service.getAll()
            : service.filterByDepartment(dept));
    }

    private void fillTable(List<Employee> list) {
        model.setRowCount(0);
        for (Employee e : list) model.addRow(e.toRow());
    }

    private void updateStatsLabel() {
        statsLabel.setText(String.format(
            "تعداد کارکنان فعال: %d | میانگین حقوق: %.0f | دپارتمان‌ها: %s",
            service.totalCount(), service.averageSalary(),
            service.countByDepartment().keySet()));
    }

    private Employee selectedEmployee() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "لطفاً یک ردیف انتخاب کنید", "هشدار", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Long id = (Long) model.getValueAt(row, 0);
        return service.getAll().stream()
            .filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }

    private void showDialog(Employee existing) {
        EmployeeDialog dialog = new EmployeeDialog(this, existing, service);
        dialog.setVisible(true);
        if (dialog.isSaved()) {
            reload();
            refreshDeptFilter();
        }
    }

    private void editSelected() {
        Employee e = selectedEmployee();
        if (e != null) showDialog(e);
    }

    private void deleteSelected() {
        Employee e = selectedEmployee();
        if (e == null) return;
        int ans = JOptionPane.showConfirmDialog(this,
            "حذف «" + e.getFullName() + "»؟", "تأیید حذف", JOptionPane.YES_NO_OPTION);
        if (ans == JOptionPane.YES_OPTION) {
            service.delete(e.getId());
            reload(); refreshDeptFilter();
        }
    }

    private void exportCsv() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            CsvExporter.export(service.getAll(), path);
            JOptionPane.showMessageDialog(this, "خروجی ذخیره شد: " + path);
        }
    }

    private void showStats() {
        Map<String, Integer> counts = service.countByDepartment();
        StringBuilder sb = new StringBuilder("توزیع کارکنان بر اساس دپارتمان:\n\n");
        counts.forEach((dept, n) -> sb.append(String.format("%-20s %d نفر%n", dept, n)));
        sb.append(String.format("%nمیانگین حقوق: %.0f", service.averageSalary()));
        JOptionPane.showMessageDialog(this, sb.toString(), "آمار", JOptionPane.INFORMATION_MESSAGE);
    }

    private void refreshDeptFilter() {
        deptFilter.removeAllItems();
        deptFilter.addItem("همهٔ دپارتمان‌ها");
        for (String d : service.countByDepartment().keySet()) deptFilter.addItem(d);
    }
}
