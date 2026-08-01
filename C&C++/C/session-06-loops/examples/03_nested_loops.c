/*
 * جلسه ۶ — حلقه‌های تودرتو: چاپ مربع ستاره
 */

#include <stdio.h>

int main(void) {
    int size = 4;

    /* حلقهٔ بیرونی: سطرها */
    for (int row = 0; row < size; row++) {
        /* حلقهٔ داخلی: ستون‌ها */
        for (int col = 0; col < size; col++) {
            printf("* ");
        }
        printf("\n");
    }

    /* مثلث: سطر i ام i+1 ستاره دارد */
    for (int i = 0; i < size; i++) {
        for (int j = 0; j <= i; j++) {
            printf("*");
        }
        printf("\n");
    }

    return 0;
}
