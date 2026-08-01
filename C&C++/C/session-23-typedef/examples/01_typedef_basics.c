/*
 * جلسه ۲۳ — typedef: نام‌های مستعار
 */

#include <stdio.h>

/* نام مستعار ساده */
typedef unsigned long long ulong;
typedef double Temperature;

int main(void) {
    ulong population = 8000000000UL;
    Temperature body_temp = 37.0;

    printf("جمعیت: %llu\n", population);
    printf("دمای بدن: %.1f\n", body_temp);

    /* typedef تغییر‌ناپذیری نمی‌دهد — فقط یک نام دیگر است */
    return 0;
}
