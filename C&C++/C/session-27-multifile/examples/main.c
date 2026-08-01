/*
 * جلسه ۲۷ — برنامهٔ اصلی که از هدر استفاده می‌کند
 * کامپایل: gcc -Wall -Wextra -o program main.c math_utils.c
 */

#include <stdio.h>
#include "math_utils.h"   /* هدر محلی با "..." */

int main(void) {
    printf("add(3, 4) = %d\n", add(3, 4));
    printf("multiply(3, 4) = %d\n", multiply(3, 4));
    printf("divide(10, 4) = %.2f\n", divide(10, 4));
    return 0;
}
