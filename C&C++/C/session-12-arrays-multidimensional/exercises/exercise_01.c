/*
 * تمرین ۱ — مجموع عناصر قطر اصلی
 * قطر اصلی: خانه‌هایی که سطرشان برابر ستون‌شان است (i == j).
 */

#include <stdio.h>

#define SIZE 3

int main(void) {
    int matrix[SIZE][SIZE] = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    int sum = 0;
    for (int i = 0; i < SIZE; i++) {
        sum += matrix[i][i];   /* قطر اصلی */
    }

    printf("مجموع قطر اصلی = %d\n", sum);   /* 1+5+9 = 15 */
    return 0;
}
