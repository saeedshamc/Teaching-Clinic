/*
 * جلسه ۱۲ — ماتریس: تعریف، مقداردهی و چاپ
 */

#include <stdio.h>

#define ROWS 3
#define COLS 3

int main(void) {
    int matrix[ROWS][COLS] = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    /* چاپ ماتریس */
    printf("ماتریس:\n");
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }

    /* دسترسی مستقیم */
    printf("عنصر [1][2] = %d\n", matrix[1][2]);

    /* مجموع عناصر */
    int sum = 0;
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            sum += matrix[i][j];
        }
    }
    printf("مجموع همهٔ عناصر = %d\n", sum);

    return 0;
}
