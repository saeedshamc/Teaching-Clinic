// TaskManagerDemo.java — مینی‌پروژهٔ جمع‌بندی فاز ۵ (بخش Swing)
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.util.*;

class Task {
    int id;
    String title;
    String description;
    String priority;
    String status;
    LocalDate dueDate;

    Task(int id, String title, String description, String priority, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = "PENDING";
        this.dueDate = dueDate;
    }
}

public class TaskManagerDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // ---------- Data ----------
            List<Task> tasks = new ArrayList<>();
            tasks.add(new Task(1, "Write report", "Q4 report", "HIGH", LocalDate.now().plusDays(3)));
            tasks.add(new Task(2, "Fix bug", "Login issue", "MEDIUM", LocalDate.now().plusDays(1)));
            tasks.add(new Task(3, "Update docs", "API docs", "LOW", LocalDate.now().plusDays(7)));

            // ---------- Frame ----------
            JFrame frame = new JFrame("Task Manager");
            frame.setSize(900, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // ---------- Top panel ----------
            JPanel topPanel = new JPanel(new FlowLayout());
            JTextField titleField = new JTextField(15);
            JTextField descField = new JTextField(15);
            JComboBox<String> priorityCombo = new JComboBox<>(new String[]{"HIGH", "MEDIUM", "LOW"});
            JButton addBtn = new JButton("Add Task");
            topPanel.add(new JLabel("Title:"));
            topPanel.add(titleField);
            topPanel.add(new JLabel("Desc:"));
            topPanel.add(descField);
            topPanel.add(new JLabel("Priority:"));
            topPanel.add(priorityCombo);
            topPanel.add(addBtn);
            frame.add(topPanel, BorderLayout.NORTH);

            // ---------- Table ----------
            String[] columns = {"ID", "Title", "Description", "Priority", "Status", "Due"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            JTable table = new JTable(model);
            JScrollPane tableScroll = new JScrollPane(table);
            frame.add(tableScroll, BorderLayout.CENTER);

            // ---------- Bottom panel ----------
            JPanel bottomPanel = new JPanel(new FlowLayout());
            JButton completeBtn = new JButton("Complete");
            JButton deleteBtn = new JButton("Delete");
            bottomPanel.add(completeBtn);
            bottomPanel.add(deleteBtn);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            // ---------- Tree for priority filter ----------
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Priority");
            root.add(new DefaultMutableTreeNode("HIGH"));
            root.add(new DefaultMutableTreeNode("MEDIUM"));
            root.add(new DefaultMutableTreeNode("LOW"));
            root.add(new DefaultMutableTreeNode("ALL"));
            JTree tree = new JTree(root);
            JScrollPane treeScroll = new JScrollPane(tree);
            treeScroll.setPreferredSize(new Dimension(120, 0));

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(treeScroll, BorderLayout.WEST);
            mainPanel.add(tableScroll, BorderLayout.CENTER);
            frame.add(mainPanel, BorderLayout.CENTER);

            // ---------- Methods ----------
            Runnable refreshTable = () -> {
                model.setRowCount(0);
                for (Task t : tasks) {
                    model.addRow(new Object[]{t.id, t.title, t.description, t.priority, t.status, t.dueDate});
                }
            };
            refreshTable.run();

            addBtn.addActionListener(e -> {
                String title = titleField.getText().trim();
                String desc = descField.getText().trim();
                String priority = (String) priorityCombo.getSelectedItem();
                if (!title.isEmpty()) {
                    tasks.add(new Task(tasks.size() + 1, title, desc, priority, LocalDate.now().plusDays(7)));
                    refreshTable.run();
                    titleField.setText("");
                    descField.setText("");
                }
            });

            completeBtn.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    Task t = tasks.get(row);
                    t.status = "COMPLETED";
                    refreshTable.run();
                }
            });

            deleteBtn.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    tasks.remove(row);
                    refreshTable.run();
                }
            });

            // ---------- TreeSelectionListener ----------
            tree.addTreeSelectionListener(e -> {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node != null) {
                    String filter = node.getUserObject().toString();
                    model.setRowCount(0);
                    for (Task t : tasks) {
                        if (filter.equals("ALL") || t.priority.equals(filter)) {
                            model.addRow(new Object[]{t.id, t.title, t.description, t.priority, t.status, t.dueDate});
                        }
                    }
                }
            });

            frame.setVisible(true);
        });
    }
}
