// CalculatorAWT.java — پاسخ پروژهٔ جلسه ۶۱
import java.awt.*;
import java.awt.event.*;

public class CalculatorAWT extends Frame {

    private TextField display;
    private String current = "";
    private String operator = "";
    private double result = 0;

    public CalculatorAWT() {
        super("Calculator");
        setSize(300, 400);
        setLayout(new BorderLayout());

        // Display
        display = new TextField("0");
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Buttons panel
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 3, 5, 5));

        String[] buttons = {"7", "8", "9", "+",
                            "4", "5", "6", "-",
                            "1", "2", "3", "*",
                            "0", "C", "=", "/"};

        for (String text : buttons) {
            Button btn = new Button(text);
            btn.addActionListener(e -> handleButton(text));
            panel.add(btn);
        }
        add(panel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    void handleButton(String text) {
        if (text.equals("C")) {
            current = "";
            operator = "";
            result = 0;
            display.setText("0");
        } else if (text.equals("=")) {
            calculate();
        } else if ("+-*/".contains(text)) {
            operator = text;
            result = Double.parseDouble(current.isEmpty() ? "0" : current);
            current = "";
        } else {
            current += text;
            display.setText(current);
        }
    }

    void calculate() {
        if (current.isEmpty()) return;
        double num = Double.parseDouble(current);
        switch (operator) {
            case "+": result += num; break;
            case "-": result -= num; break;
            case "*": result *= num; break;
            case "/": result /= num; break;
        }
        display.setText(String.valueOf(result));
        current = "";
        operator = "";
    }

    public static void main(String[] args) {
        new CalculatorAWT();
    }
}
