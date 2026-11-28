// CalculatorEvents.java — پاسخ پروژهٔ جلسه ۶۴
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorEvents {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Calculator with Events");
            frame.setSize(300, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JTextField display = new JTextField("0");
            display.setEditable(false);
            display.setHorizontalAlignment(SwingConstants.RIGHT);
            frame.add(display, BorderLayout.NORTH);

            JPanel panel = new JPanel(new GridLayout(4, 3, 5, 5));
            String[] buttons = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", "C", "=", "/"};

            for (String text : buttons) {
                JButton btn = new JButton(text);
                btn.addActionListener(e -> {
                    String current = display.getText();
                    if (text.equals("C")) {
                        display.setText("0");
                    } else if (text.equals("=")) {
                        try {
                            String[] parts = current.split("(?<=[+\\-*/])|(?=[+\\-*/])");
                            // simple eval — برای demo
                            display.setText("Result: " + current);
                        } catch (Exception ex) {
                            display.setText("Error");
                        }
                    } else {
                        if (current.equals("0") || current.startsWith("Result")) {
                            display.setText(text);
                        } else {
                            display.setText(current + text);
                        }
                    }
                });
                panel.add(btn);
            }

            // ---------- MouseListener برای right-click clear ----------
            panel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        display.setText("0");
                    }
                }
            });

            frame.add(panel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
