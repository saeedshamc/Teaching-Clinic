// RegistrationSWT.java — پاسخ پروژهٔ جلسه ۷۴
// نیازمند SWT jar
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class RegistrationSWT {

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Registration SWT");
        shell.setSize(350, 250);
        shell.setLayout(new GridLayout(2, false));

        // ---------- Fields ----------
        new Label(shell, SWT.NONE).setText("Name:");
        Text nameText = new Text(shell, SWT.BORDER);

        new Label(shell, SWT.NONE).setText("Email:");
        Text emailText = new Text(shell, SWT.BORDER);

        new Label(shell, SWT.NONE).setText("Password:");
        Text passText = new Text(shell, SWT.BORDER | SWT.PASSWORD);

        // ---------- Submit ----------
        Button submitBtn = new Button(shell, SWT.PUSH);
        submitBtn.setText("Submit");
        GridData gd = new GridData();
        gd.horizontalSpan = 2;
        submitBtn.setLayoutData(gd);

        submitBtn.addListener(SWT.Selection, e -> {
            String name = nameText.getText();
            String email = emailText.getText();
            String pass = passText.getText();
            if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                System.out.println("All fields required");
            } else {
                System.out.println("Registered: " + name + " (" + email + ")");
            }
        });

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
