// ContactManager.java — پاسخ پروژهٔ جلسه ۶۵
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactManager {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Contact Manager");
            frame.setSize(800, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // ---------- Data ----------
            Map<String, List<String[]>> contacts = new HashMap<>();
            contacts.put("Friends", java.util.Arrays.<String[]>asList(
                new String[]{"Ali", "123-4567", "ali@mail.com"},
                new String[]{"Sara", "987-6543", "sara@mail.com"}
            ));
            contacts.put("Family", java.util.Arrays.<String[]>asList(
                new String[]{"Dad", "555-0001", "dad@mail.com"},
                new String[]{"Mom", "555-0002", "mom@mail.com"}
            ));
            contacts.put("Work", java.util.Arrays.<String[]>asList(
                new String[]{"Boss", "555-9999", "boss@company.com"}
            ));

            // ---------- Left: Tree ----------
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categories");
            for (String cat : contacts.keySet()) {
                root.add(new DefaultMutableTreeNode(cat));
            }
            JTree tree = new JTree(root);
            JScrollPane treeScroll = new JScrollPane(tree);
            treeScroll.setPreferredSize(new Dimension(150, 0));

            // ---------- Right: Table ----------
            String[] columns = {"Name", "Phone", "Email"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            JTable table = new JTable(model);
            JScrollPane tableScroll = new JScrollPane(table);

            // ---------- TreeSelectionListener ----------
            tree.addTreeSelectionListener(e -> {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node != null && node.getParent() != null) {
                    String category = node.getUserObject().toString();
                    model.setRowCount(0);   // clear
                    for (String[] contact : contacts.get(category)) {
                        model.addRow(contact);
                    }
                }
            });

            // ---------- Buttons ----------
            JPanel buttons = new JPanel();
            JButton addBtn = new JButton("Add");
            JButton removeBtn = new JButton("Remove");
            buttons.add(addBtn);
            buttons.add(removeBtn);

            addBtn.addActionListener(e -> {
                String name = JOptionPane.showInputDialog(frame, "Name:");
                String phone = JOptionPane.showInputDialog(frame, "Phone:");
                String email = JOptionPane.showInputDialog(frame, "Email:");
                if (name != null && phone != null && email != null) {
                    model.addRow(new Object[]{name, phone, email});
                }
            });

            removeBtn.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    model.removeRow(row);
                }
            });

            // ---------- Layout ----------
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(treeScroll, BorderLayout.WEST);
            mainPanel.add(tableScroll, BorderLayout.CENTER);

            frame.add(mainPanel, BorderLayout.CENTER);
            frame.add(buttons, BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }
}
