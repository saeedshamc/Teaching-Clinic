/*
 * جلسه ۸ — اعلان (prototype) و توابع void
 */

#include <stdio.h>

/* اعلان تابع: بدون بدنه، با سمی‌کالن */
int max_of_three(int a, int b, int c);

/* تابع void که چیزی برنمی‌گرداند */
void print_line(int count) {
    for (int i = 0; i < count; i++) {
        printf("-");
    }
    printf("\n");
}

int main(void) {
    print_line(20);
    printf("بزرگ‌ترین ۳ و ۷ و ۵: %d\n", max_of_three(3, 7, 5));
    print_line(10);
    return 0;
}

/* تعریف تابع بعد از main — به لطف اعلان بالا */
int max_of_three(int a, int b, int c) {
    int max = a;
    if (b > max) {
        max = b;
    }
    if (c > max) {
        max = c;
    }
    return max;
}
