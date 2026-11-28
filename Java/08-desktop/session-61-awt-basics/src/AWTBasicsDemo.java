// AWTBasicsDemo.java — Frame، کامپوننت‌ها، layout، ActionListener
// ⚠️ این یک برنامهٔ GUI است — برای اجرا باید display داشته باشید
import java.awt.*;
import java.awt.event.*;

public class AWTBasicsDemo extends Frame {

    private Label statusLabel;

    public AWTBasicsDemo() {
        super("AWT Basics Demo");
        setSize(400, 300);
        setLayout(new FlowLayout());

        // ---------- Button ----------
        Button btn = new Button("Click Me");
        btn.addActionListener(e -> {
            statusLabel.setText("Button clicked at " + System.currentTimeMillis());
        });
        add(btn);

        // ---------- Label ----------
        statusLabel = new Label("Ready");
        add(statusLabel);

        // ---------- TextField ----------
        TextField tf = new TextField(20);
        tf.addActionListener(e -> {
            statusLabel.setText("You typed: " + tf.getText());
        });
        add(tf);

        // ---------- Checkbox ----------
        Checkbox cb = new Checkbox("Enable feature");
        cb.addItemListener(e -> {
            statusLabel.setText("Checkbox: " + (cb.getState() ? "ON" : "OFF"));
        });
        add(cb);

        // ---------- Window listener ----------
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new AWTBasicsDemo();
    }
}
