// DataManagerSWT.java — پاسخ پروژهٔ جلسه ۷۵
// نیازمند SWT jar
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class DataManagerSWT {

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Data Manager SWT");
        shell.setSize(800, 600);

        // ---------- Menu ----------
        Menu menuBar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menuBar);
        Menu fileMenu = new Menu(menuBar, SWT.DROP_DOWN);
        fileMenu.setText("File");
        menuBar.setMenu(0, fileMenu);
        MenuItem newItem = new MenuItem(fileMenu, SWT.PUSH);
        newItem.setText("New");
        newItem.addListener(SWT.Selection, e -> System.out.println("New"));

        // ---------- Layout ----------
        shell.setLayout(new GridLayout(1, false));

        // ---------- TabFolder ----------
        TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
        tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        // ---------- Tab 1: Data ----------
        TabItem dataTab = new TabItem(tabFolder, SWT.NONE);
        dataTab.setText("Data");
        Composite dataContent = new Composite(tabFolder, SWT.NONE);
        dataTab.setControl(dataContent);
        dataContent.setLayout(new GridLayout(1, false));

        // Table
        Table table = new Table(dataContent, SWT.BORDER | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        TableColumn nameCol = new TableColumn(table, SWT.NONE);
        nameCol.setText("Name"); nameCol.setWidth(150);
        TableColumn phoneCol = new TableColumn(table, SWT.NONE);
        phoneCol.setText("Phone"); phoneCol.setWidth(150);
        TableColumn emailCol = new TableColumn(table, SWT.NONE);
        emailCol.setText("Email"); emailCol.setWidth(150);

        TableItem item1 = new TableItem(table, SWT.NONE);
        item1.setText(0, "Ali"); item1.setText(1, "123-4567"); item1.setText(2, "ali@mail.com");
        TableItem item2 = new TableItem(table, SWT.NONE);
        item2.setText(0, "Sara"); item2.setText(1, "987-6543"); item2.setText(2, "sara@mail.com");

        // ---------- Tab 2: Settings ----------
        TabItem settingsTab = new TabItem(tabFolder, SWT.NONE);
        settingsTab.setText("Settings");
        Composite settingsContent = new Composite(tabFolder, SWT.NONE);
        settingsTab.setControl(settingsContent);
        settingsContent.setLayout(new GridLayout(2, false));

        new Label(settingsContent, SWT.NONE).setText("Theme:");
        Combo themeCombo = new Combo(settingsContent, SWT.DROP_DOWN | SWT.READ_ONLY);
        themeCombo.setItems(new String[]{"Light", "Dark", "System"});
        themeCombo.select(0);

        new Label(settingsContent, SWT.NONE).setText("Language:");
        Combo langCombo = new Combo(settingsContent, SWT.DROP_DOWN | SWT.READ_ONLY);
        langCombo.setItems(new String[]{"English", "Persian", "Arabic"});
        langCombo.select(0);

        // ---------- Tab 3: Help ----------
        TabItem helpTab = new TabItem(tabFolder, SWT.NONE);
        helpTab.setText("Help");
        Composite helpContent = new Composite(tabFolder, SWT.NONE);
        helpTab.setControl(helpContent);
        helpContent.setLayout(new GridLayout(1, false));

        Label helpLabel = new Label(helpContent, SWT.NONE);
        helpLabel.setText("This is a help page.\nUse File menu for actions.");

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
