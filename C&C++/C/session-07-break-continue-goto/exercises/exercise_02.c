/*
 * تمرین ۲ — اعداد اول ۲ تا ۵۰
 * عدد اول یعنی هیچ مقسوم‌علیه‌ای به جز ۱ و خودش ندارد.
 */

#include <stdio.h>

int main(void) {
    printf("اعداد اول ۲ تا ۵۰: ");
    for (int n = 2; n <= 50; n++) {
        int is_prime = 1;

        /* بررسی مقسوم‌علیه‌ها از ۲ تا n-1 */
        for (int d = 2; d < n; d++) {
            if (n % d == 0) {
                is_prime = 0;
                break;   /* دیگر لازم نیست ادامه بدهیم */
            }
        }

        if (is_prime) {
            printf("%d ", n);
        }
    }
    printf("\n");
    return 0;
}
