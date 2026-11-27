// Converter.java — پاسخ پروژهٔ جلسه ۱۲
public class Converter {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Converter <value> <from> <to>");
            return;
        }
        double value = Double.parseDouble(args[0]);
        String from = args[1].toLowerCase();
        String to = args[2].toLowerCase();
        double result;

        // دما
        if (from.equals("c") && to.equals("f")) {
            result = value * 9.0 / 5.0 + 32;
        } else if (from.equals("f") && to.equals("c")) {
            result = (value - 32) * 5.0 / 9.0;
        } else if (from.equals("k") && to.equals("m")) {
            result = value * 1000;
        } else if (from.equals("m") && to.equals("k")) {
            result = value / 1000;
        } else {
            System.out.println("Unsupported conversion");
            return;
        }

        System.out.printf("%.2f %s = %.2f %s%n", value, from, result, to);
    }
}
