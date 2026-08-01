/*
 * جلسه ۲۶ — #define و ماکروهای با آرگومان
 */

#include <stdio.h>

#define PI 3.14159
#define SQUARE(x) ((x) * (x))
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int main(void) {
    printf("PI = %f\n", PI);
    printf("SQUARE(5) = %d\n", SQUARE(5));
    /* بدون پرانتز این اشتباه می‌شد: 2+3*2+3 = 11 */
    printf("SQUARE(2+3) = %d\n", SQUARE(2 + 3));
    printf("MAX(7, 3) = %d\n", MAX(7, 3));

    int x = 10;
    printf("MAX(x, 15) = %d\n", MAX(x, 15));

    return 0;
}
