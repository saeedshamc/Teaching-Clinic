// AWTLegacyDemo.java — AWT basics برای فهم کد قدیمی
// ⚠️ این فقط برای یادگیری است — برای پروژهٔ جدید از JavaFX یا Web استفاده کنید
import java.awt.*;
import java.awt.event.*;

public class AWTLegacyDemo extends Frame {

    public AWTLegacyDemo() {
        super("AWT Legacy Demo");
        setSize(400, 300);
        setLayout(new FlowLayout());

        // Button
        Button btn = new Button("Click Me");
        btn.addActionListener(e -> {
            System.out.println("Button clicked!");
        });
        add(btn);

        // Label
        Label label = new Label("This is a label");
        add(label);

        // TextField
        TextField tf = new TextField(20);
        tf.addActionListener(e -> {
            System.out.println("Text: " + tf.getText());
        });
        add(tf);

        // Window listener برای close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new AWTLegacyDemo();
    }
}
