// EmployeeManagementSystem.java — نقطهٔ ورود برنامه
// اجرا: java -cp out EmployeeManagementSystem
import javax.swing.SwingUtilities;

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
