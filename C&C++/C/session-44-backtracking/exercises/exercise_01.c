/*
 * تمرین ۱ — N-Queen برای N=4 با چاپ همهٔ راه‌حل‌ها
 * N=4 دقیقاً ۲ راه‌حل دارد
 */

#include <stdio.h>

#define N 4

int solutions = 0;

int is_safe(int board[N][N], int row, int col) {
    for (int i = 0; i < row; i++) {
        if (board[i][col]) {
            return 0;
        }
    }
    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j]) {
            return 0;
        }
    }
    for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
        if (board[i][j]) {
            return 0;
        }
    }
    return 1;
}

/* همهٔ راه‌حل‌ها را می‌شماریم و چاپ می‌کنیم */
void solve_all(int board[N][N], int row) {
    if (row == N) {
        solutions++;
        printf("راه‌حل %d:\n", solutions);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                printf("%c ", board[i][j] ? 'Q' : '.');
            }
            printf("\n");
        }
        printf("\n");
        return;
    }

    for (int col = 0; col < N; col++) {
        if (is_safe(board, row, col)) {
            board[row][col] = 1;
            solve_all(board, row + 1);
            board[row][col] = 0;   /* BACKTRACK */
        }
    }
}

int main(void) {
    int board[N][N] = {0};
    solve_all(board, 0);
    printf("تعداد کل راه‌حل‌ها برای %d وزیر: %d\n", N, solutions);
    return 0;
}
