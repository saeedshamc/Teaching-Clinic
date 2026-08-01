/*
 * جلسه ۱۵ — مبانی اشاره‌گر: & و *
 */

#include <stdio.h>

int main(void) {
    int x = 10;
    int *p = &x;   /* p آدرس x را نگه می‌دارد */

    printf("مقدار x: %d\n", x);
    printf("آدرس x (&x): %p\n", (void *)&x);
    printf("مقدار p (آدرس): %p\n", (void *)p);
    printf("مقدار *p (مقدار x): %d\n", *p);

    /* تغییر مقدار از طریق اشاره‌گر */
    *p = 42;
    printf("بعد از *p = 42، مقدار x: %d\n", x);

    /* تغییر خود اشاره‌گر */
    int y = 99;
    p = &y;
    printf("حالا *p (یعنی y): %d\n", *p);

    /* اشاره‌گر به double */
    double pi = 3.14;
    double *dp = &pi;
    printf("پی از طریق اشاره‌گر: %.2f\n", *dp);

    return 0;
}
