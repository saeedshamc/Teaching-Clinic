// SWTWidgetsDemo.java — Table، Tree، TabFolder، Menu، GridData
// ⚠️ نیازمند SWT jar
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class SWTWidgetsDemo {

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("SWT Widgets Demo");
        shell.setSize(600, 500);

        // ---------- Menu ----------
        Menu menuBar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menuBar);
        Menu fileMenu = new Menu(menuBar, SWT.DROP_DOWN);
        fileMenu.setText("File");
        menuBar.setMenu(0, fileMenu);
        MenuItem newItem = new MenuItem(fileMenu, SWT.PUSH);
        newItem.setText("New");
        newItem.addListener(SWT.Selection, e -> System.out.println("New"));

        shell.setLayout(new GridLayout(1, false));

        // ---------- TabFolder ----------
        TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
        GridData tabGd = new GridData(SWT.FILL, SWT.FILL, true, true);
        tabFolder.setLayoutData(tabGd);

        // ---------- Tab 1: Table ----------
        TabItem tab1 = new TabItem(tabFolder, SWT.NONE);
        tab1.setText("Table");
        Composite tab1Content = new Composite(tabFolder, SWT.NONE);
        tab1.setControl(tab1Content);
        tab1Content.setLayout(new GridLayout(1, false));

        Table table = new Table(tab1Content, SWT.BORDER | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        TableColumn nameCol = new TableColumn(table, SWT.NONE);
        nameCol.setText("Name");
        nameCol.setWidth(150);
        TableColumn phoneCol = new TableColumn(table, SWT.NONE);
        phoneCol.setText("Phone");
        phoneCol.setWidth(150);

        TableItem item1 = new TableItem(table, SWT.NONE);
        item1.setText(0, "Ali"); item1.setText(1, "123-4567");
        TableItem item2 = new TableItem(table, SWT.NONE);
        item2.setText(0, "Sara"); item2.setText(1, "987-6543");

        // ---------- Tab 2: Tree ----------
        TabItem tab2 = new TabItem(tabFolder, SWT.NONE);
        tab2.setText("Tree");
        Composite tab2Content = new Composite(tabFolder, SWT.NONE);
        tab2.setControl(tab2Content);
        tab2Content.setLayout(new GridLayout(1, false));

        Tree tree = new Tree(tab2Content, SWT.BORDER);
        tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        TreeItem root = new TreeItem(tree, SWT.NONE);
        root.setText("Root");
        TreeItem child1 = new TreeItem(root, SWT.NONE);
        child1.setText("Child 1");
        TreeItem grandchild = new TreeItem(child1, SWT.NONE);
        grandchild.setText("Grandchild");
        TreeItem child2 = new TreeItem(root, SWT.NONE);
        child2.setText("Child 2");

        // ---------- Tab 3: Form ----------
        TabItem tab3 = new TabItem(tabFolder, SWT.NONE);
        tab3.setText("Form");
        Composite tab3Content = new Composite(tabFolder, SWT.NONE);
        tab3.setControl(tab3Content);
        tab3Content.setLayout(new GridLayout(2, false));

        new Label(tab3Content, SWT.NONE).setText("Name:");
        new Text(tab3Content, SWT.BORDER);
        new Label(tab3Content, SWT.NONE).setText("Email:");
        new Text(tab3Content, SWT.BORDER);
        Button submitBtn = new Button(tab3Content, SWT.PUSH);
        submitBtn.setText("Submit");

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
