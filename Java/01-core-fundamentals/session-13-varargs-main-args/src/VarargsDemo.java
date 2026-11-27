// VarargsDemo.java — args در main + varargs
public class VarargsDemo {

    // ---------- main با args ----------
    public static void main(String[] args) {
        System.out.println("args.length=" + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "]=" + args[i]);
        }

        // صدا زدن متدهای varargs
        print("a");
        print("a", "b", "c");
        print();   // آرایهٔ خالی

        printNumbers(1, 2, 3, 4, 5);
        printNumbers();   // بدون آرگومان

        log("INFO", "User", "logged", "in");
        log("ERROR", "Failed", "to", "connect");

        // args را به یک متد varargs بدهید
        printAll(args);
    }

    // ---------- varargs ساده ----------
    static void print(String... words) {
        System.out.println("print count=" + words.length);
        for (String w : words) System.out.print(w + " ");
        System.out.println();
    }

    // ---------- varargs با محاسبه ----------
    static void printNumbers(int... nums) {
        int sum = 0;
        for (int n : nums) sum += n;
        System.out.println("sum=" + sum + " count=" + nums.length);
    }

    // ---------- ترکیب پارامتر + varargs ----------
    static void log(String level, String... messages) {
        System.out.print("[" + level + "] ");
        for (String m : messages) System.out.print(m + " ");
        System.out.println();
    }

    // ---------- varargs برای همهٔ args ----------
    static void printAll(String... args) {
        System.out.println("printAll count=" + args.length);
    }
}
