// SumArgs.java — پاسخ پروژهٔ جلسه ۱۳
public class SumArgs {

    // varargs برای اعداد — می‌توانید هر تعداد آرگومان بدهید
    static void analyze(int... nums) {
        if (nums.length == 0) {
            System.out.println("Usage: java SumArgs <numbers...>");
            return;
        }
        int sum = 0, max = nums[0], min = nums[0];
        for (int n : nums) {
            sum += n;
            if (n > max) max = n;
            if (n < min) min = n;
        }
        double avg = (double) sum / nums.length;
        System.out.printf("Sum: %d, Avg: %.1f, Max: %d, Min: %d%n",
                sum, avg, max, min);
    }

    public static void main(String[] args) {
        // args (String[]) را به int[] تبدیل کنید
        int[] numbers = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            numbers[i] = Integer.parseInt(args[i]);
        }
        analyze(numbers);   // آرایهٔ int به varargs پاس می‌شود
    }
}
