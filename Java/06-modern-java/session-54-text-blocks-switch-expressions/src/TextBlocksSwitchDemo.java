// TextBlocksSwitchDemo.java — text blocks، formatted، stripIndent، switch expressions، yield
public class TextBlocksSwitchDemo {

    public static void main(String[] args) {
        // ---------- text block ساده ----------
        String json = """
            {
              "name": "Ali",
              "age": 20,
              "city": "Tehran"
            }
            """;
        System.out.println("JSON:\n" + json);

        // ---------- text block با formatted ----------
        String name = "Sara";
        int age = 25;
        String msg = """
            Hello %s, you are %d years old
            Welcome to the system!
            """.formatted(name, age);
        System.out.println("\nFormatted:\n" + msg);

        // ---------- text block برای code ----------
        String code = """
            public class Main {
                public static void main(String[] args) {
                    System.out.println("Hello");
                }
            }
            """.stripIndent();
        System.out.println("\nCode:\n" + code);

        // ---------- switch expression ----------
        String day = "SATURDAY";
        String result = switch (day) {
            case "SATURDAY", "SUNDAY" -> "Weekend";
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            default -> "Unknown";
        };
        System.out.println("\nDay type: " + result);

        // ---------- switch با yield ----------
        int num = 3;
        String numStr = switch (num) {
            case 1 -> "One";
            case 2 -> "Two";
            case 3 -> {
                String msg2 = "Three";
                yield msg2 + " (odd)";
            }
            case 4 -> "Four";
            default -> "Many";
        };
        System.out.println("Number: " + numStr);

        // ---------- switch با multiple labels ----------
        int score = 85;
        String grade = switch (score / 10) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "F";
        };
        System.out.println("Grade: " + grade);

        // ---------- switch روی enum ----------
        enum Color { RED, GREEN, BLUE }
        Color color = Color.GREEN;
        String colorDesc = switch (color) {
            case RED -> "Red — stop";
            case GREEN -> "Green — go";
            case BLUE -> "Blue — caution";
        };
        System.out.println("Color: " + colorDesc);

        // ---------- text block برای HTML ----------
        String html = """
            <html>
                <body>
                    <h1>Hello World</h1>
                    <p>This is a test</p>
                </body>
            </html>
            """;
        System.out.println("\nHTML:\n" + html);
    }
}
