// Dice.java — پاسخ پروژهٔ جلسه ۱۴
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    public static void main(String[] args) {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 1;
        int[] dice = new int[n];
        int sum = 0, max = 0, min = 7;

        for (int i = 0; i < n; i++) {
            dice[i] = ThreadLocalRandom.current().nextInt(1, 7);   // 1..6
            sum += dice[i];
            max = Math.max(max, dice[i]);
            min = Math.min(min, dice[i]);
        }

        System.out.print("Dice:");
        for (int d : dice) System.out.print(" " + d);
        System.out.println();

        BigDecimal avg = BigDecimal.valueOf(sum)
                .divide(BigDecimal.valueOf(n), 2, RoundingMode.HALF_UP);
        System.out.printf("Sum: %d, Avg: %s, Max: %d, Min: %d%n",
                sum, avg, max, min);
    }
}
