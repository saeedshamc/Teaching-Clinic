/*
 * جلسه ۵ — if / else if / else و شرط سه‌تایی
 */

#include <stdio.h>

int main(void) {
    int score;

    printf("نمره (۰ تا ۲۰): ");
    if (scanf("%d", &score) != 1) {
        printf("ورودی نامعتبر!\n");
        return 1;
    }

    /* if / else if / else */
    if (score >= 17) {
        printf("عالی!\n");
    } else if (score >= 14) {
        printf("خیلی خوب\n");
    } else if (score >= 10) {
        printf("قابل قبول\n");
    } else {
        printf("مردود\n");
    }

    /* شرط سه‌تایی */
    int a = 7, b = 9;
    int max = (a > b) ? a : b;
    printf("بزرگ‌ترین %d و %d برابر %d است\n", a, b, max);

    return 0;
}
