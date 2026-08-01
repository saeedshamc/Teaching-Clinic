/*
 * تمرین ۲ — جمع دو ماتریس ۲×۲
 */

#include <stdio.h>

#define ROWS 2
#define COLS 2

int main(void) {
    int a[ROWS][COLS] = {{1, 2}, {3, 4}};
    int b[ROWS][COLS] = {{5, 6}, {7, 8}};
    int result[ROWS][COLS];

    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            result[i][j] = a[i][j] + b[i][j];
        }
    }

    printf("نتیجهٔ جمع:\n");
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            printf("%d ", result[i][j]);
        }
        printf("\n");
    }
    return 0;
}
