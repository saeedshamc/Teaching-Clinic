// HelloJava.java — ساده‌ترین برنامهٔ جاوا
// هر فایل .java که کلاس public دارد باید هم‌نام کلاس باشد.
public class HelloJava {

    // main متد نقطهٔ شروع برنامه است؛ امضایش باید دقیقاً همین باشد.
    // String[] args آرگومان‌های خط فرمان را می‌گیرد (جلسهٔ ۱۳).
    public static void main(String[] args) {
        // System.out یک PrintStream است؛ printlnln خروجی + خط جدید می‌دهد.
        System.out.println("Hello, Java 17!");

        // برای اجرای بدون IDE:
        //   javac src/HelloJava.java   → HelloJava.class ساخته می‌شود
        //   java -cp src HelloJava    → اجرا
    }
}
