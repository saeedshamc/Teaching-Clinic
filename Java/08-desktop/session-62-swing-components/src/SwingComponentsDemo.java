// SwingComponentsDemo.java — JFrame، JPanel، JButton، JLabel، JOptionPane
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingComponentsDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // ---------- JFrame ----------
            JFrame frame = new JFrame("Swing Components Demo");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // ---------- JPanel NORTH ----------
            JPanel topPanel = new JPanel();
            topPanel.add(new JLabel("Welcome to Swing!"));
            frame.add(topPanel, BorderLayout.NORTH);

            // ---------- JPanel CENTER ----------
            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new FlowLayout());

            // JButton
            JButton btn = new JButton("Click Me");
            btn.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame,
                    "Button clicked!",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
            });
            centerPanel.add(btn);

            // JLabel
            centerPanel.add(new JLabel("This is a label"));

            // JTextField
            JTextField tf = new JTextField(15);
            tf.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame,
                    "You typed: " + tf.getText(),
                    "Input",
                    JOptionPane.PLAIN_MESSAGE);
            });
            centerPanel.add(tf);

            // JCheckBox
            JCheckBox cb = new JCheckBox("Enable feature");
            cb.addItemListener(e -> {
                System.out.println("Checkbox: " + (cb.isSelected() ? "ON" : "OFF"));
            });
            centerPanel.add(cb);

            // JComboBox
            JComboBox<String> combo = new JComboBox<>(new String[]{"Option A", "Option B", "Option C"});
            combo.addActionListener(e -> {
                System.out.println("Selected: " + combo.getSelectedItem());
            });
            centerPanel.add(combo);

            frame.add(centerPanel, BorderLayout.CENTER);

            // ---------- JPanel SOUTH ----------
            JPanel bottomPanel = new JPanel();
            JButton confirmBtn = new JButton("Confirm");
            confirmBtn.addActionListener(e -> {
                int result = JOptionPane.showConfirmDialog(frame,
                    "Are you sure?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Confirmed!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            bottomPanel.add(confirmBtn);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }
}
