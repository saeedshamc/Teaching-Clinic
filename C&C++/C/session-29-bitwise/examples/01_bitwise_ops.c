/*
 * جلسه ۲۹ — عملگرهای بیتی پایه
 */

#include <stdio.h>

int main(void) {
    unsigned int a = 0b1010;   /* 10 */
    unsigned int b = 0b1100;   /* 12 */

    printf("a & b  = %u\n", a & b);    /* 1000 = 8  */
    printf("a | b  = %u\n", a | b);    /* 1110 = 14 */
    printf("a ^ b  = %u\n", a ^ b);    /* 0110 = 6  */
    printf("~a     = %u\n", ~a);

    /* شیفت: ضرب و تقسیم در توان ۲ */
    unsigned int x = 5;
    printf("5 << 3 = %u (ضرب در ۸)\n", x << 3);   /* 40 */
    printf("40 >> 2 = %u (تقسیم بر ۴)\n", (x << 3) >> 2);  /* 10 */

    /* یک بیت خاص */
    unsigned int flags = 0b0101;
    unsigned int mask = 0b0010;
    printf("بیت دوم ست است؟ %u\n", (flags & mask) != 0);   /* 0 */

    return 0;
}
