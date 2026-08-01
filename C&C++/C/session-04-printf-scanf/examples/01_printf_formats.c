/*
 * جلسه ۴ — مشخص‌کننده‌های printf
 */

#include <stdio.h>

int main(void) {
    int age = 25;
    long long population = 8000000000LL;   /* long long برای اعداد بزرگ */
    char letter = 'C';
    const char *name = "علی";
    double pi = 3.14159265358979;

    printf("سن: %d\n", age);
    printf("جمعیت: %lld\n", population);
    printf("حرف: %c\n", letter);
    printf("نام: %s\n", name);
    printf("پی: %f\n", pi);
    printf("پی با ۲ رقم اعشار: %.2f\n", pi);
    printf("درصد: ۱۰۰%%\n");     /* %% برای چاپ خودِ ٪ */
    printf("قد (مثلاً ۱۰): %5d\n", 10);  /* عرض حداقلی ۵ */

    return 0;
}
