// RegistrationForm.java — پاسخ پروژهٔ جلسه ۶۲
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationForm {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Registration Form");
            frame.setSize(400, 350);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(6, 2, 10, 10));

            // Fields
            JLabel nameLabel = new JLabel("Name:");
            JTextField nameField = new JTextField(20);

            JLabel emailLabel = new JLabel("Email:");
            JTextField emailField = new JTextField(20);

            JLabel passLabel = new JLabel("Password:");
            JPasswordField passField = new JPasswordField(20);

            JCheckBox termsBox = new JCheckBox("I agree to terms");

            JButton submitBtn = new JButton("Submit");
            submitBtn.addActionListener(e -> {
                // Validation
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String pass = new String(passField.getPassword());

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Name is required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(frame, "Invalid email", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (pass.length() < 6) {
                    JOptionPane.showMessageDialog(frame, "Password too short", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!termsBox.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "You must agree to terms", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(frame,
                    "Registration successful!\nWelcome, " + name,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            });

            // Layout
            frame.add(nameLabel);
            frame.add(nameField);
            frame.add(emailLabel);
            frame.add(emailField);
            frame.add(passLabel);
            frame.add(passField);
            frame.add(termsBox);
            frame.add(new JLabel());   // spacer
            frame.add(new JLabel());   // spacer
            frame.add(submitBtn);

            frame.setVisible(true);
        });
    }
}
