// SwingLayoutsDemo.java — BorderLayout، FlowLayout، GridLayout، BoxLayout، CardLayout
import javax.swing.*;
import java.awt.*;

public class SwingLayoutsDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Swing Layouts Demo");
            frame.setSize(600, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // ---------- Tabbed pane برای هر layout ----------
            JTabbedPane tabs = new JTabbedPane();

            // ---------- BorderLayout ----------
            JPanel borderPanel = new JPanel(new BorderLayout());
            borderPanel.add(new JButton("North"), BorderLayout.NORTH);
            borderPanel.add(new JButton("South"), BorderLayout.SOUTH);
            borderPanel.add(new JButton("East"), BorderLayout.EAST);
            borderPanel.add(new JButton("West"), BorderLayout.WEST);
            borderPanel.add(new JButton("Center"), BorderLayout.CENTER);
            tabs.addTab("BorderLayout", borderPanel);

            // ---------- FlowLayout ----------
            JPanel flowPanel = new JPanel(new FlowLayout());
            flowPanel.add(new JButton("Button 1"));
            flowPanel.add(new JButton("Button 2"));
            flowPanel.add(new JButton("Button 3"));
            flowPanel.add(new JButton("Button 4"));
            flowPanel.add(new JButton("Button 5"));
            tabs.addTab("FlowLayout", flowPanel);

            // ---------- GridLayout ----------
            JPanel gridPanel = new JPanel(new GridLayout(3, 3, 5, 5));
            for (int i = 1; i <= 9; i++) {
                gridPanel.add(new JButton(String.valueOf(i)));
            }
            tabs.addTab("GridLayout", gridPanel);

            // ---------- BoxLayout ----------
            JPanel boxPanel = new JPanel();
            boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
            boxPanel.add(new JLabel("Top"));
            boxPanel.add(Box.createVerticalStrut(10));
            boxPanel.add(new JButton("Middle"));
            boxPanel.add(Box.createVerticalGlue());
            boxPanel.add(new JButton("Bottom"));
            tabs.addTab("BoxLayout", boxPanel);

            // ---------- CardLayout ----------
            JPanel cards = new JPanel(new CardLayout());
            cards.add(new JLabel("Card 1 — Welcome!"), "card1");
            cards.add(new JLabel("Card 2 — About"), "card2");
            cards.add(new JLabel("Card 3 — Settings"), "card3");

            CardLayout cl = (CardLayout) cards.getLayout();
            JPanel cardPanel = new JPanel(new BorderLayout());
            cardPanel.add(cards, BorderLayout.CENTER);
            JPanel cardButtons = new JPanel();
            cardButtons.add(new JButton("Card 1") {{ addActionListener(e -> cl.show(cards, "card1")); }});
            cardButtons.add(new JButton("Card 2") {{ addActionListener(e -> cl.show(cards, "card2")); }});
            cardButtons.add(new JButton("Card 3") {{ addActionListener(e -> cl.show(cards, "card3")); }});
            cardPanel.add(cardButtons, BorderLayout.SOUTH);
            tabs.addTab("CardLayout", cardPanel);

            frame.add(tabs);
            frame.setVisible(true);
        });
    }
}
