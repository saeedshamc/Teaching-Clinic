// SwingEventsDemo.java — ActionListener، ItemListener، MouseListener، KeyListener، adapters
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingEventsDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Swing Events Demo");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JLabel status = new JLabel("Ready");
            status.setHorizontalAlignment(SwingConstants.CENTER);
            frame.add(status, BorderLayout.SOUTH);

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());

            // ---------- ActionListener ----------
            JButton btn = new JButton("Click Me");
            btn.addActionListener(e -> {
                status.setText("Button clicked: " + e.getActionCommand());
            });
            panel.add(btn);

            // ---------- ItemListener ----------
            JCheckBox cb = new JCheckBox("Enable");
            cb.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    status.setText("Checkbox: ON");
                } else {
                    status.setText("Checkbox: OFF");
                }
            });
            panel.add(cb);

            // ---------- JComboBox ----------
            JComboBox<String> combo = new JComboBox<>(new String[]{"A", "B", "C"});
            combo.addActionListener(e -> {
                status.setText("Selected: " + combo.getSelectedItem());
            });
            panel.add(combo);

            // ---------- MouseListener (adapter) ----------
            JPanel mousePanel = new JPanel();
            mousePanel.setPreferredSize(new Dimension(200, 100));
            mousePanel.setBackground(Color.LIGHT_GRAY);
            mousePanel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    status.setText("Mouse clicked at " + e.getX() + "," + e.getY());
                }
                public void mouseEntered(MouseEvent e) {
                    status.setText("Mouse entered panel");
                }
                public void mouseExited(MouseEvent e) {
                    status.setText("Mouse exited panel");
                }
            });
            panel.add(mousePanel);

            // ---------- KeyListener (adapter) ----------
            JTextField tf = new JTextField(15);
            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    status.setText("Key pressed: " + e.getKeyChar());
                }
                public void keyReleased(KeyEvent e) {
                    status.setText("Key released");
                }
            });
            panel.add(tf);

            frame.add(panel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
