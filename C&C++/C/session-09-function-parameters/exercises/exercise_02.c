/*
 * تمرین ۲ — مجموع و حاصل‌ضرب با اشاره‌گر (پیش‌نمایش جلسهٔ ۱۷)
 * اشاره‌گر یعنی آدرس متغیر؛ تابع از طریق آدرس می‌تواند متغیر بیرونی را تغییر دهد.
 */

#include <stdio.h>

void get_sum_and_product(int a, int b, int *sum, int *product) {
    *sum = a + b;       /* در آدرسِ sum بنویس */
    *product = a * b;   /* در آدرسِ product بنویس */
}

int main(void) {
    int s = 0, p = 0;
    get_sum_and_product(6, 7, &s, &p);   /* & آدرس متغیر را می‌دهد */
    printf("مجموع = %d، حاصل‌ضرب = %d\n", s, p);
    return 0;
}
