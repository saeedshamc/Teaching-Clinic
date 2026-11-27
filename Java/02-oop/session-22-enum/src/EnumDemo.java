// EnumDemo.java — enum ساده، با فیلد/متد، valueOf/values، switch روی enum
public class EnumDemo {

    // ---------- enum ساده ----------
    enum Color {
        RED, GREEN, BLUE, YELLOW
    }

    // ---------- enum با فیلد و متد ----------
    enum Status {
        PENDING("در انتظار", 0),
        IN_PROGRESS("در حال انجام", 1),
        DONE("تمام شده", 2);

        private final String label;
        private final int code;

        Status(String label, int code) {
            this.label = label;
            this.code = code;
        }

        public String getLabel() { return label; }
        public int getCode() { return code; }
    }

    // ---------- enum با abstract method ----------
    enum Operation {
        ADD {
            @Override double apply(double a, double b) { return a + b; }
        },
        SUBTRACT {
            @Override double apply(double a, double b) { return a - b; }
        },
        MULTIPLY {
            @Override double apply(double a, double b) { return a * b; }
        },
        DIVIDE {
            @Override double apply(double a, double b) { return b == 0 ? 0 : a / b; }
        };

        abstract double apply(double a, double b);
    }

    public static void main(String[] args) {
        // ---------- enum ساده ----------
        Color c = Color.RED;
        System.out.println("Color=" + c);              // RED
        System.out.println("ordinal=" + c.ordinal());   // 0
        System.out.println("values count=" + Color.values().length); // 4

        for (Color col : Color.values()) {
            System.out.print(col + " ");
        }
        System.out.println();

        // ---------- enum با فیلد ----------
        Status s = Status.valueOf("DONE");   // رشته → enum
        System.out.println("Status=" + s.getLabel() + " code=" + s.getCode());

        for (Status st : Status.values()) {
            System.out.println(st.name() + " → " + st.getLabel());
        }

        // ---------- switch روی enum ----------
        Status current = Status.IN_PROGRESS;
        switch (current) {
            case PENDING -> System.out.println("Waiting...");
            case IN_PROGRESS -> System.out.println("Working...");
            case DONE -> System.out.println("Finished!");
        }

        // ---------- enum با abstract method ----------
        System.out.println("10 + 5 = " + Operation.ADD.apply(10, 5));
        System.out.println("10 - 5 = " + Operation.SUBTRACT.apply(10, 5));
        System.out.println("10 * 5 = " + Operation.MULTIPLY.apply(10, 5));
        System.out.println("10 / 5 = " + Operation.DIVIDE.apply(10, 5));
    }
}
