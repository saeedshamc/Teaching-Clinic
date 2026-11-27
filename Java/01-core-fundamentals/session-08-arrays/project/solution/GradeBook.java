// GradeBook.java — پاسخ پروژهٔ جلسه ۰۸
import java.util.Arrays;

public class GradeBook {
    public static void main(String[] args) {
        // ۵ دانش‌آموز × ۳ نمره
        double[][] grades = {
            {17.0, 18.0, 16.0},
            {15.0, 16.0, 15.5},
            {18.0, 19.0, 17.5},
            {14.0, 15.0, 13.5},
            {16.5, 17.0, 18.0}
        };

        double bestAvg = -1;
        int bestStudent = -1;

        for (int i = 0; i < grades.length; i++) {
            // مرتب‌سازی نمرات هر دانش‌آموز
            Arrays.sort(grades[i]);

            // میانگین با حلقه
            double sum = 0;
            for (int j = 0; j < grades[i].length; j++) {
                sum += grades[i][j];
            }
            double avg = sum / grades[i].length;

            System.out.printf("Student %d avg=%.1f%n", i, avg);

            if (avg > bestAvg) {
                bestAvg = avg;
                bestStudent = i;
            }
        }

        System.out.println("Top student: " + bestStudent + " with avg " + bestAvg);
    }
}
