/*
 * تمرین ۱ — تابع swap با اشاره‌گر
 * TODO: تابع swap را با اشاره‌گر کامل کنید تا دو متغیر جابه‌جا شوند.
 */

#include <stdio.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

int main(void) {
    int x = 10, y = 20;
    printf("قبل: x=%d, y=%d\n", x, y);
    swap(&x, &y);
    printf("بعد: x=%d, y=%d\n", x, y);
    return 0;
}
