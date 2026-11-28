// SwingTablesTreesDemo.java — JTable با DefaultTableModel، JTree با DefaultTreeModel
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import java.awt.*;

public class SwingTablesTreesDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tables and Trees Demo");
            frame.setSize(700, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JTabbedPane tabs = new JTabbedPane();

            // ---------- JTable ----------
            String[] columns = {"Name", "Age", "City"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return true;   // همه cellها قابل ویرایش
                }
            };

            model.addRow(new Object[]{"Ali", 20, "Tehran"});
            model.addRow(new Object[]{"Sara", 25, "Isfahan"});
            model.addRow(new Object[]{"Reza", 30, "Shiraz"});

            JTable table = new JTable(model);
            JScrollPane tableScroll = new JScrollPane(table);
            tabs.addTab("Table", tableScroll);

            // ---------- JTree ----------
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
            DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("Child 1");
            DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("Child 2");
            root.add(child1);
            root.add(child2);
            child1.add(new DefaultMutableTreeNode("Grandchild 1"));
            child1.add(new DefaultMutableTreeNode("Grandchild 2"));
            child2.add(new DefaultMutableTreeNode("Grandchild 3"));

            JTree tree = new JTree(root);
            JScrollPane treeScroll = new JScrollPane(tree);
            tabs.addTab("Tree", treeScroll);

            // ---------- TreeSelectionListener ----------
            tree.addTreeSelectionListener(e -> {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node != null) {
                    System.out.println("Selected: " + node.getUserObject());
                }
            });

            // ---------- TableModelListener برای تغییرات ----------
            model.addTableModelListener(e -> {
                System.out.println("Table changed at row " + e.getFirstRow() + ", col " + e.getColumn());
            });

            frame.add(tabs);
            frame.setVisible(true);
        });
    }
}
