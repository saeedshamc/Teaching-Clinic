// TaskManagerFull.java — پاسخ کامل پروژهٔ جلسه ۶۶
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.util.*;

enum Priority { HIGH, MEDIUM, LOW }
enum Status { PENDING, IN_PROGRESS, COMPLETED }

class Task {
    int id;
    String title;
    String description;
    Priority priority;
    Status status;
    LocalDate dueDate;

    Task(int id, String title, String description, Priority priority, LocalDate dueDate) {
        this.id = id; this.title = title; this.description = description;
        this.priority = priority; this.status = Status.PENDING; this.dueDate = dueDate;
    }
}

public class TaskManagerFull {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Task> tasks = new ArrayList<>();
            tasks.add(new Task(1, "Write report", "Q4 report", Priority.HIGH, LocalDate.now().plusDays(3)));
            tasks.add(new Task(2, "Fix bug", "Login issue", Priority.MEDIUM, LocalDate.now().plusDays(1)));
            tasks.add(new Task(3, "Update docs", "API docs", Priority.LOW, LocalDate.now().plusDays(7)));

            JFrame frame = new JFrame("Task Manager Full");
            frame.setSize(900, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // ---------- Top ----------
            JPanel top = new JPanel(new FlowLayout());
            JTextField titleField = new JTextField(15);
            JTextField descField = new JTextField(15);
            JComboBox<Priority> priorityCombo = new JComboBox<>(Priority.values());
            JButton addBtn = new JButton("Add");
            top.add(new JLabel("Title:")); top.add(titleField);
            top.add(new JLabel("Desc:")); top.add(descField);
            top.add(new JLabel("Priority:")); top.add(priorityCombo);
            top.add(addBtn);
            frame.add(top, BorderLayout.NORTH);

            // ---------- Tabs ----------
            JTabbedPane tabs = new JTabbedPane();

            // ---------- Table ----------
            String[] columns = {"ID", "Title", "Description", "Priority", "Status", "Due"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            JTable table = new JTable(model);
            JScrollPane tableScroll = new JScrollPane(table);
            tabs.addTab("All Tasks", tableScroll);

            // ---------- Tree ----------
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Priority");
            for (Priority p : Priority.values()) root.add(new DefaultMutableTreeNode(p.name()));
            root.add(new DefaultMutableTreeNode("ALL"));
            JTree tree = new JTree(root);
            JScrollPane treeScroll = new JScrollPane(tree);
            treeScroll.setPreferredSize(new Dimension(120, 0));

            JPanel main = new JPanel(new BorderLayout());
            main.add(treeScroll, BorderLayout.WEST);
            main.add(tabs, BorderLayout.CENTER);
            frame.add(main, BorderLayout.CENTER);

            // ---------- Bottom ----------
            JPanel bottom = new JPanel(new FlowLayout());
            JButton completeBtn = new JButton("Complete");
            JButton deleteBtn = new JButton("Delete");
            bottom.add(completeBtn); bottom.add(deleteBtn);
            frame.add(bottom, BorderLayout.SOUTH);

            // ---------- Refresh ----------
            Runnable refresh = () -> {
                model.setRowCount(0);
                for (Task t : tasks) {
                    model.addRow(new Object[]{t.id, t.title, t.description, t.priority, t.status, t.dueDate});
                }
            };
            refresh.run();

            // ---------- Events ----------
            addBtn.addActionListener(e -> {
                String title = titleField.getText().trim();
                String desc = descField.getText().trim();
                Priority priority = (Priority) priorityCombo.getSelectedItem();
                if (!title.isEmpty()) {
                    tasks.add(new Task(tasks.size() + 1, title, desc, priority, LocalDate.now().plusDays(7)));
                    refresh.run();
                    titleField.setText(""); descField.setText("");
                }
            });

            completeBtn.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) { tasks.get(row).status = Status.COMPLETED; refresh.run(); }
            });

            deleteBtn.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) { tasks.remove(row); refresh.run(); }
            });

            // ---------- Tree filter ----------
            tree.addTreeSelectionListener(e -> {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node != null) {
                    String filter = node.getUserObject().toString();
                    model.setRowCount(0);
                    for (Task t : tasks) {
                        if (filter.equals("ALL") || t.priority.name().equals(filter)) {
                            model.addRow(new Object[]{t.id, t.title, t.description, t.priority, t.status, t.dueDate});
                        }
                    }
                }
            });

            frame.setVisible(true);
        });
    }
}
