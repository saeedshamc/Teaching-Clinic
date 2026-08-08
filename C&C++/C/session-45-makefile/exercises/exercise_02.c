/*
 * تمرین ۲ — پروژهٔ دو فایلی برای Makefile
 *
 * یک فایل utils_ex.c بسازید که این توابع را پیاده‌سازی کند و یک هدر
 * utils_ex.h با پروتوتایپ‌ها، سپس Makefile با قواعد جداگانه برای
 * main و utils بنویسید. CFLAGS = -Wall -Wextra -g
 */

#include <stdio.h>
#include "utils_ex.h"

int main(void) {
    int nums[] = {4, 9, 2, 7, 5};
    int n = (int)(sizeof(nums) / sizeof(nums[0]));

    printf("بزرگ‌ترین: %d\n", max_of(nums, n));
    printf("کوچک‌ترین: %d\n", min_of(nums, n));
    printf("میانگین: %.2f\n", avg_of(nums, n));

    return 0;
}
