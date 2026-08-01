/*
 * تمرین ۱ — مجموع بازگشتی ۱ تا n
 * TODO: تابع sum_to را بازگشتی بنویسید:
 * sum_to(5) = 5 + sum_to(4) = ... = 15
 */

#include <stdio.h>

/* TODO: پیاده‌سازی بازگشتی */
int sum_to(int n) {
    if (n <= 0) {
        return 0;              /* شرط پایه */
    }
    return n + sum_to(n - 1);  /* شرط بازگشتی */
}

int main(void) {
    printf("sum_to(5) = %d\n", sum_to(5));
    printf("sum_to(10) = %d\n", sum_to(10));
    return 0;
}
