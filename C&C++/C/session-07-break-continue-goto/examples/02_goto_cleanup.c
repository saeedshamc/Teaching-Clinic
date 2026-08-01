/*
 * جلسه ۷ — goto برای خروج هم‌زمان از چند حلقه
 */

#include <stdio.h>

int main(void) {
    /* پیدا کردن مختصات اولین جایی که i+j == 7 */
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            if (i + j == 7) {
                printf("پیدا شد: i=%d, j=%d\n", i, j);
                goto found;   /* خروج از هر دو حلقه */
            }
        }
    }
    printf("پیدا نشد\n");

found:
    printf("پایان برنامه\n");
    return 0;
}
