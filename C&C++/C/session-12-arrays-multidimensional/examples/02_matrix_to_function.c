/*
 * جلسه ۱۲ — عبور ماتریس به تابع
 * نکته: تعداد ستون‌ها باید در امضای تابع مشخص باشد.
 */

#include <stdio.h>

#define COLS 3

/* تعداد ستون‌ها الزامی است؛ تعداد سطرها می‌تواند باز بماند */
void print_matrix(int m[][COLS], int rows) {
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < COLS; j++) {
            printf("%d ", m[i][j]);
        }
        printf("\n");
    }
}

/* تغییر عناصر از داخل تابع هم ممکن است */
void set_all(int m[][COLS], int rows, int value) {
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < COLS; j++) {
            m[i][j] = value;
        }
    }
}

int main(void) {
    int m[2][COLS] = {
        {1, 2, 3},
        {4, 5, 6}
    };

    printf("قبل:\n");
    print_matrix(m, 2);

    set_all(m, 2, 0);
    printf("بعد از صفر کردن:\n");
    print_matrix(m, 2);

    return 0;
}
