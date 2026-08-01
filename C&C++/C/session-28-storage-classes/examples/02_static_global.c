/*
 * جلسه ۲۸ — static سراسری: محدود به همین فایل
 * با static جلوی متغیر سراسری، از فایل‌های دیگر قابل دسترسی نیست.
 */

#include <stdio.h>

static int internal_counter = 0;   /* فقط همین فایل */

void bump(void) {
    internal_counter++;
}

int get_counter(void) {
    return internal_counter;
}

int main(void) {
    bump();
    bump();
    bump();
    printf("شمارنده: %d\n", get_counter());
    return 0;
}
