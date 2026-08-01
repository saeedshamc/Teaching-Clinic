/*
 * جلسه ۷ — break و continue
 */

#include <stdio.h>

int main(void) {
    /* continue: اعداد زوج رد شوند */
    printf("اعداد فرد ۱ تا ۱۰: ");
    for (int i = 1; i <= 10; i++) {
        if (i % 2 == 0) {
            continue;   /* به تکرار بعدی برو */
        }
        printf("%d ", i);
    }
    printf("\n");

    /* break: در عدد ۵ متوقف شو */
    printf("تا ۵ ادامه: ");
    for (int i = 0; i < 10; i++) {
        if (i == 5) {
            break;      /* خروج از حلقه */
        }
        printf("%d ", i);
    }
    printf("\n");

    /* جستجو: اولین مضرب ۷ از ۱ تا ۱۰۰ */
    for (int i = 1; i <= 100; i++) {
        if (i % 7 == 0) {
            printf("اولین مضرب ۷: %d\n", i);
            break;
        }
    }

    return 0;
}
