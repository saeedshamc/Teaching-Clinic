/*
 * جلسه ۹ — ارسال با مقدار: چرا تغییر داخل تابع بیرون نمی‌ماند؟
 */

#include <stdio.h>

void increment(int x) {
    x = x + 1;
    printf("داخل تابع: x = %d\n", x);
}

int main(void) {
    int n = 5;
    printf("قبل: n = %d\n", n);
    increment(n);
    printf("بعد از صدا زدن: n = %d\n", n);   /* هنوز ۵ است */

    /* چرا؟ چون increment کپیِ n را گرفت، نه خودِ n را */
    return 0;
}
