/*
 * جلسه ۲ — ثابت‌ها (const) و sizeof
 */

#include <stdio.h>
#include <limits.h>   /* برای INT_MAX و ... */

int main(void) {
    /* ثابت: بعد از مقداردهی قابل تغییر نیست */
    const double PI = 3.14159;
    printf("عدد پی: %f\n", PI);

    /* sizeof: اندازه به بایت */
    printf("اندازه char: %zu بایت\n", sizeof(char));
    printf("اندازه int: %zu بایت\n", sizeof(int));
    printf("اندازه float: %zu بایت\n", sizeof(float));
    printf("اندازه double: %zu بایت\n", sizeof(double));

    /* محدوده‌ها از limits.h */
    printf("بزرگ‌ترین int: %d\n", INT_MAX);
    printf("کوچک‌ترین int: %d\n", INT_MIN);

    return 0;
}
