/*
 * تمرین ۱ — چاپ ۱ تا ۱۰ و مجموع آن‌ها
 * TODO: ۱) با for اعداد ۱ تا ۱۰ را چاپ کنید.
 *       ۲) با while مجموع آن‌ها را حساب و چاپ کنید (پاسخ ۵۵).
 */

#include <stdio.h>

int main(void) {
    printf("اعداد ۱ تا ۱۰: ");

    /* TODO: حلقهٔ for برای چاپ ۱ تا ۱۰ */
    for (int i = 1; i <= 10; i++) {
        printf("%d ", i);
    }
    printf("\n");

    int sum = 0;
    int n = 1;

    /* TODO: حلقهٔ while برای مجموع ۱ تا ۱۰ */
    while (n <= 10) {
        sum += n;
        n++;
    }

    printf("مجموع = %d\n", sum);
    return 0;
}
