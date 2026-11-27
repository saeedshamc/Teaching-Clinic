// CommonClassesDemo.java — Math، Random، BigInteger/BigDecimal، Date/Calendar
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CommonClassesDemo {

    public static void main(String[] args) {
        // ---------- Math ----------
        System.out.println("Math.abs(-5)=" + Math.abs(-5));
        System.out.println("Math.max(3,7)=" + Math.max(3, 7));
        System.out.println("Math.min(3,7)=" + Math.min(3, 7));
        System.out.println("Math.pow(2,10)=" + Math.pow(2, 10));
        System.out.println("Math.sqrt(16)=" + Math.sqrt(16));
        System.out.println("Math.ceil(3.2)=" + Math.ceil(3.2));
        System.out.println("Math.floor(3.8)=" + Math.floor(3.8));
        System.out.println("Math.round(3.5)=" + Math.round(3.5));
        System.out.println("Math.random()=" + Math.random());

        // ---------- Random ----------
        Random rand = new Random();
        System.out.println("rand.nextInt()=" + rand.nextInt());
        System.out.println("rand.nextInt(100)=" + rand.nextInt(100));
        System.out.println("rand.nextDouble()=" + rand.nextDouble());
        System.out.println("rand.nextBoolean()=" + rand.nextBoolean());

        // ThreadLocalRandom — thread-safe برای concurrent
        int n = ThreadLocalRandom.current().nextInt(1, 100);   // 1..99
        System.out.println("ThreadLocalRandom 1..99=" + n);

        // ---------- BigInteger ----------
        BigInteger big = new BigInteger("123456789012345678901234567890");
        BigInteger sum = big.add(new BigInteger("1"));
        System.out.println("BigInteger sum=" + sum);

        BigInteger fact = BigInteger.ONE;
        for (int i = 1; i <= 30; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        System.out.println("30!=" + fact);   // بزرگ‌تر از long

        // ---------- BigDecimal ----------
        BigDecimal price = new BigDecimal("19.99");
        BigDecimal tax = price.multiply(new BigDecimal("0.09"));
        BigDecimal total = price.add(tax);
        System.out.println("BigDecimal total=" + total);   // 21.7891

        // تله: new BigDecimal(0.1) → 0.1000000000000000055511151231257827
        BigDecimal bad = new BigDecimal(0.1);
        BigDecimal good = new BigDecimal("0.1");
        System.out.println("bad=" + bad);
        System.out.println("good=" + good);

        // ---------- Date و Calendar ----------
        Date now = new Date();
        System.out.println("Date now=" + now);

        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 15);   // JANUARY=0 نه 1!
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date future = cal.getTime();
        System.out.println("Calendar +7d=" + future);
    }
}
