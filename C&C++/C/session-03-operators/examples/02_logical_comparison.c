/*
 * جلسه ۳ — عملگرهای مقایسه‌ای و منطقی
 */

#include <stdio.h>

int main(void) {
    int age = 20;
    int has_ticket = 1;   /* 1 یعنی بلیت دارد */

    /* مقایسه: نتیجه ۱ یا ۰ */
    printf("age > 18 ? %d\n", age > 18);
    printf("age == 20 ? %d\n", age == 20);
    printf("age != 21 ? %d\n", age != 21);

    /* منطقی */
    printf("(age > 18 && has_ticket) ? %d\n", age > 18 && has_ticket);
    printf("(age < 18 || has_ticket) ? %d\n", age < 18 || has_ticket);
    printf("(!has_ticket) ? %d\n", !has_ticket);

    /* اولویت: ضرب قبل از جمع */
    int result = 1 + 2 * 3;      /* 7 */
    printf("1 + 2 * 3 = %d\n", result);
    printf("(1 + 2) * 3 = %d\n", (1 + 2) * 3);   /* 9 */

    return 0;
}
