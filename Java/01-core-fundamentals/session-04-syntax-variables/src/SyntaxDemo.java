// SyntaxDemo.java — ساختار کامل فایل + متغیرها و نام‌گذاری
// برای کامپایل بدون IDE: javac -d out src/SyntaxDemo.java && java -cp out SyntaxDemo
// (این فایل در ریشهٔ src است چون package ندارد — برای پروژه‌های واقعی package بگذارید)

public class SyntaxDemo {

    // ---------- فیلدهای کلاس ----------
    // static = متعلق به کلاس نه instance؛ هر دو نمونه به یک counter دسترسی دارند.
    static int counter = 0;
    // static final = ثابت — بعد از مقداردهی اولیه تغییر نمی‌کند (compile-time constant).
    static final int MAX_SIZE = 100;

    // ---------- متد main — نقطهٔ شروع ----------
    public static void main(String[] args) {
        // ----- متغیرهای primitive -----
        byte small = 100;                    // ۸ بیتی: -128 تا 127
        short s = 1000;                      // ۱۶ بیتی
        int age = 25;                        // ۳۲ بیتی — پرکاربردترین
        long big = 9_000_000_000L;           // ۶۴ بیتی — L suffix برای literal بزرگ
        float f = 1.5f;                      // ۳۲ بیتی اعشار — f suffix
        double d = 19.99;                    // ۶۴ بیتی اعشار — پیش‌فرض برای اعشار
        char grade = 'A';                    // ۱۶ بیتی یونیکد — single quote
        boolean isActive = true;             // فقط true یا false

        // ----- String (شیء، نه primitive) -----
        String name = "Ali";                 // double quote
        String empty = "";                   // رشتهٔ خالی معتبر است

        // ----- var (Java 10+) — فقط برای local، نه فیلد -----
        var score = 95.5;                    // double حدس زده می‌شود
        var items = new int[3];              // int[] حدس زده می‌شود

        // ----- ثابت‌ها و نام‌گذاری -----
        // MAX_SIZE در بالا تعریف شد — UPPER_SNAKE برای static final

        // ----- چاپ خروجی -----
        System.out.println("name=" + name + ", age=" + age);
        System.out.println("counter before=" + counter);
        increment();                          // متد static را صدا می‌زند
        System.out.println("counter after=" + counter);
    }

    // متد static — بدون ساخت instance صدا زده می‌شود.
    static void increment() {
        counter++;                            // فیلد static را تغییر می‌دهد
    }
}
