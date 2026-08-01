/*
 * تمرین ۲ — چاپ آدرس و بررسی اندازهٔ اشاره‌گرها
 */

#include <stdio.h>

int main(void) {
    int i = 1;
    double d = 2.5;
    char c = 'A';

    int *ip = &i;
    double *dp = &d;
    char *cp = &c;

    printf("آدرس int:    %p\n", (void *)ip);
    printf("آدرس double: %p\n", (void *)dp);
    printf("آدرس char:   %p\n", (void *)cp);

    printf("اندازهٔ اشاره‌گر int:    %zu بایت\n", sizeof(ip));
    printf("اندازهٔ اشاره‌گر double: %zu بایت\n", sizeof(dp));
    printf("اندازهٔ اشاره‌گر char:   %zu بایت\n", sizeof(cp));

    return 0;
}
