// SWTBasicsDemo.java — Display، Shell، Button، Label، event loop
// ⚠️ نیازمند SWT jar — برای کامپایل: javac -cp swt.jar
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class SWTBasicsDemo {

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("SWT Basics Demo");
        shell.setSize(400, 300);

        shell.setLayout(new GridLayout(1, false));

        // ---------- Label ----------
        Label title = new Label(shell, SWT.NONE);
        title.setText("Welcome to SWT!");

        // ---------- Button ----------
        Button btn = new Button(shell, SWT.PUSH);
        btn.setText("Click Me");
        btn.addListener(SWT.Selection, e -> {
            System.out.println("Button clicked!");
        });

        // ---------- Text ----------
        Text text = new Text(shell, SWT.BORDER);
        text.setMessage("Enter text...");

        // ---------- Event loop ----------
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
