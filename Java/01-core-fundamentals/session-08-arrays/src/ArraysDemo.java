// ArraysDemo.java — آرایهٔ یک‌بعدی، چندبعدی، Arrays utility، for-each
import java.util.Arrays;

public class ArraysDemo {

    public static void main(String[] args) {
        // ---------- تعریف ----------
        int[] numbers = {5, 2, 8, 1, 9};
        int[] empty = new int[5];          // همه 0
        String[] names = {"Ali", "Sara", "Reza"};

        System.out.println("length=" + numbers.length);   // 5
        System.out.println("numbers[0]=" + numbers[0]);   // 5

        // ---------- پیمایش: index loop ----------
        System.out.println("index loop:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        // ---------- پیمایش: for-each ----------
        System.out.println("for-each:");
        for (int n : numbers) {
            System.out.print(n + " ");
        }
        System.out.println();

        // ---------- آرایهٔ چندبعدی ----------
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("matrix[1][2]=" + matrix[1][2]);   // 6
        System.out.println("matrix rows=" + matrix.length);    // 3
        System.out.println("matrix[0] cols=" + matrix[0].length); // 3

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // ---------- Arrays utility ----------
        int[] nums = {5, 2, 8, 1, 9};
        Arrays.sort(nums);                              // مرتب‌سازی
        System.out.println("sorted=" + Arrays.toString(nums)); // [1, 2, 5, 8, 9]

        int idx = Arrays.binarySearch(nums, 5);
        System.out.println("binarySearch(5)=" + idx);   // 2

        Arrays.fill(nums, 0, 3, -1);                    // nums[0..2] = -1
        System.out.println("after fill=" + Arrays.toString(nums)); // [-1, -1, -1, 8, 9]

        int[] copy = Arrays.copyOf(nums, 7);            // کپی با اندازهٔ جدید
        System.out.println("copy len=" + copy.length);  // 7

        boolean eq = Arrays.equals(nums, copy);         // مقایسهٔ محتوا
        System.out.println("equals=" + eq);             // false (طول متفاوت)
    }
}
