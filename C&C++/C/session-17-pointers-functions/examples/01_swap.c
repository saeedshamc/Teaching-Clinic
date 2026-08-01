/*
 * جلسه ۱۷ — تابع swap واقعی با اشاره‌گر
 */

#include <stdio.h>

/* با آدرس، می‌توانیم خودِ متغیرهای بیرونی را عوض کنیم */
void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

int main(void) {
    int x = 10, y = 20;

    printf("قبل: x=%d, y=%d\n", x, y);
    swap(&x, &y);   /* آدرس‌ها را می‌دهیم */
    printf("بعد: x=%d, y=%d\n", x, y);

    return 0;
}
