// StringDemo.java — immutability، متدها، equals vs ==، StringBuilder، format
public class StringDemo {

    public static void main(String[] args) {
        // ---------- immutability ----------
        String s = "Hello";
        s.toUpperCase();                    // مقدار جدید می‌سازد ولی برنمی‌گرداند
        System.out.println(s);              // Hello — تغییر نکرد
        s = s.toUpperCase();                // باید assign کرد
        System.out.println(s);              // HELLO

        // ---------- متدهای رایج ----------
        String name = "Ali Rezaei";
        System.out.println("length=" + name.length());        // 11
        System.out.println("charAt(0)=" + name.charAt(0));    // A
        System.out.println("indexOf(Rezaei)=" + name.indexOf("Rezaei")); // 4
        System.out.println("substring(4)=" + name.substring(4));         // Rezaei
        System.out.println("substring(4,9)=" + name.substring(4, 9));    // Rezae
        System.out.println("contains(Ali)=" + name.contains("Ali"));     // true
        System.out.println("startsWith=" + name.startsWith("Ali"));      // true
        System.out.println("endsWith=" + name.endsWith("ei"));           // true
        System.out.println("equalsIgnoreCase=" + name.equalsIgnoreCase("ali rezaei")); // true
        System.out.println("toUpperCase=" + name.toUpperCase());
        System.out.println("toLowerCase=" + name.toLowerCase());
        System.out.println("trim=" + "  Ali  ".trim());
        System.out.println("replace=" + name.replace('a', 'A'));
        String[] parts = name.split(" ");
        System.out.println("split=" + java.util.Arrays.toString(parts));

        // ---------- equals در برابر == ----------
        String a = new String("Hello");
        String b = new String("Hello");
        System.out.println("a == b → " + (a == b));       // false — reference
        System.out.println("a.equals(b) → " + a.equals(b)); // true — محتوا
        String c = "Hello", d = "Hello";
        System.out.println("c == d (literal) → " + (c == d)); // true — string pool

        // ---------- StringBuilder در حلقه ----------
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            sb.append(i).append(", ");
        }
        System.out.println("StringBuilder=" + sb);   // 1, 2, 3, 4, 5,

        // concat با + در حلقه — کند برای رشته‌های بزرگ
        String slow = "";
        for (int i = 0; i < 5; i++) slow += i;
        System.out.println("slow concat=" + slow);   // 01234

        // ---------- format ----------
        String formatted = String.format("Name: %s, Age: %d, Score: %.2f", "Ali", 20, 17.5);
        System.out.println(formatted);
        System.out.printf("%s is %d years old%n", "Ali", 20);
    }
}
