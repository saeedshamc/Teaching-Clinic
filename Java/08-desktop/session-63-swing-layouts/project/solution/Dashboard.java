// Dashboard.java — پاسخ پروژهٔ جلسه ۶۳
import javax.swing.*;
import java.awt.*;

public class Dashboard {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dashboard");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // ---------- Header ----------
            JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
            header.add(new JLabel("Dashboard"));
            header.add(new JButton("Settings"));
            header.add(new JButton("Logout"));
            frame.add(header, BorderLayout.NORTH);

            // ---------- Sidebar ----------
            JPanel sidebar = new JPanel();
            sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
            sidebar.add(new JButton("Dashboard"));
            sidebar.add(Box.createVerticalStrut(5));
            sidebar.add(new JButton("Reports"));
            sidebar.add(Box.createVerticalStrut(5));
            sidebar.add(new JButton("Users"));
            sidebar.add(Box.createVerticalGlue());
            sidebar.add(new JButton("Help"));
            frame.add(sidebar, BorderLayout.WEST);

            // ---------- Content ----------
            JPanel content = new JPanel(new BorderLayout());

            // Stats cards — GridLayout
            JPanel stats = new JPanel(new GridLayout(1, 3, 10, 10));
            stats.add(new JLabel("Users: 150"));
            stats.add(new JLabel("Revenue: $50k"));
            stats.add(new JLabel("Growth: 12%"));
            content.add(stats, BorderLayout.NORTH);

            // Main content — CardLayout
            JPanel cards = new JPanel(new CardLayout());
            cards.add(new JLabel("Dashboard Content"), "dashboard");
            cards.add(new JLabel("Reports Content"), "reports");
            cards.add(new JLabel("Users Content"), "users");
            content.add(cards, BorderLayout.CENTER);

            frame.add(content, BorderLayout.CENTER);

            // ---------- Footer ----------
            JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
            footer.add(new JLabel("© 2024 Dashboard"));
            frame.add(footer, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }
}
