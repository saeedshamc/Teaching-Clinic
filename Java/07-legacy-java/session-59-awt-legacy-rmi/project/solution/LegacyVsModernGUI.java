// LegacyVsModernGUI.java — پاسخ پروژهٔ جلسه ۵۹
// مقایسهٔ AWT و Swing — فقط برای یادگیری
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LegacyVsModernGUI {

    // ---------- AWT version ----------
    static class AWTVersion extends Frame {
        AWTVersion() {
            super("AWT Version");
            setSize(300, 200);
            setLayout(new FlowLayout());

            Button btn = new Button("Click Me");
            btn.addActionListener(e -> System.out.println("AWT Button clicked"));
            add(btn);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dispose();
                    System.exit(0);
                }
            });
            setVisible(true);
        }
    }

    // ---------- Swing version ----------
    static class SwingVersion extends JFrame {
        SwingVersion() {
            super("Swing Version");
            setSize(300, 200);
            setLayout(new FlowLayout());

            JButton btn = new JButton("Click Me");
            btn.addActionListener(e -> System.out.println("Swing Button clicked"));
            add(btn);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== AWT vs Swing ===");
        System.out.println("AWT: native components — platform-specific appearance");
        System.out.println("Swing: pure Java — consistent cross-platform");
        System.out.println("AWT: limited widgets — Button, Label, TextField");
        System.out.println("Swing: rich widgets — JButton, JLabel, JTextField, JTable");
        System.out.println("AWT: single-threaded EDT — limited");
        System.out.println("Swing: better threading model");
        System.out.println("\n=== RMI vs REST ===");
        System.out.println("RMI: complex setup — registry, serialization");
        System.out.println("REST: simple — HTTP, JSON, language-agnostic");
        System.out.println("RMI: firewall-unfriendly — binary protocol");
        System.out.println("REST: firewall-friendly — HTTP/HTTPS");
        System.out.println("RMI: Java-only — tight coupling");
        System.out.println("REST: language-agnostic — loose coupling");
        System.out.println("\nRecommendation: Use Swing/JavaFX for GUI, REST for web services");
    }
}
