/*
 * جلسه ۴۵ — پروژهٔ چندفایلی نمونه (main.c)
 * ساخت با Makefile موجود در همین پوشه
 */

#include <stdio.h>
#include "utils.h"

int main(void) {
    int a = 12, b = 8;

    printf("%d + %d = %d\n", a, b, add(a, b));
    printf("%d - %d = %d\n", a, b, subtract(a, b));
    printf("%d * %d = %d\n", a, b, multiply(a, b));
    printf("%d / %d = %d\n", a, b, divide(a, b));

    return 0;
}
