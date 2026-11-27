// CastingDemo.java — widening/narrowing، boxing/unboxing، parse، overflow
public class CastingDemo {

    public static void main(String[] args) {
        // ---------- implicit cast (widening) ----------
        byte b = 10;
        short s = b;
        int i = s;
        long l = i;
        double d = l;
        float f = (float) d;
        System.out.println("widening: " + b + " " + s + " " + i + " " + l + " " + d);

        // ---------- explicit cast (narrowing) ----------
        double d2 = 3.99;
        int i2 = (int) d2;           // 3 — truncate نه round
        System.out.println("(int)3.99 = " + i2);

        long big = 9_000_000_000L;
        int overflow = (int) big;    // overflow — wrap می‌شود
        System.out.println("(int)9e9 = " + overflow);   // -1294967296 یا مشابه

        byte b2 = (byte) 300;        // 300 mod 256 = 44
        System.out.println("(byte)300 = " + b2);

        // ---------- boxing / unboxing ----------
        int x = 42;
        Integer boxed = x;           // boxing
        int unboxed = boxed;         // unboxing
        System.out.println("boxed=" + boxed + " unboxed=" + unboxed);

        Integer nullBoxed = null;
        // int n = nullBoxed;       // NullPointerException!

        // ---------- تبدیل رشته ↔ عدد ----------
        String str = "42";
        int parsed = Integer.parseInt(str);
        double parsedD = Double.parseDouble("3.14");
        long parsedL = Long.parseLong("1000000");
        System.out.println("parseInt=" + parsed + " parseDouble=" + parsedD + " parseLong=" + parsedL);

        String back = Integer.toString(parsed);
        String val = String.valueOf(parsedD);
        System.out.println("toString=" + back + " valueOf=" + val);

        // concat با رشته — "42" + 1 = "421" نه 43
        System.out.println("\"42\" + 1 = " + ("42" + 1));
        System.out.println("42 + \"1\" = " + (42 + "1"));

        // ---------- parse error ----------
        try {
            int bad = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: " + e.getMessage());
        }
    }
}
