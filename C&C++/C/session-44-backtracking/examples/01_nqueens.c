/*
 * جلسه ۴۴ — مسئلهٔ N-Queen (نسخهٔ یک راه‌حل)
 */

#include <stdio.h>

#define N 8

/* بررسی امن بودن قرار دادن وزیر در (row, col) */
int is_safe(int board[N][N], int row, int col) {
    /* ستون: وزیرهای ردیف‌های بالاتر نباید در همین ستون باشند */
    for (int i = 0; i < row; i++) {
        if (board[i][col]) {
            return 0;
        }
    }

    /* قطر اصلی (بالا-چپ) */
    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j]) {
            return 0;
        }
    }

    /* قطر فرعی (بالا-راست) */
    for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
        if (board[i][j]) {
            return 0;
        }
    }

    return 1;
}

/* حل بازگشتی: ردیف به ردیف وزیر می‌گذاریم */
int solve_nqueens(int board[N][N], int row) {
    if (row == N) {
        return 1;   /* همهٔ وزیرها جا گرفتند */
    }

    for (int col = 0; col < N; col++) {
        if (is_safe(board, row, col)) {
            board[row][col] = 1;   /* وزیر بگذار */

            if (solve_nqueens(board, row + 1)) {
                return 1;
            }

            board[row][col] = 0;   /* BACKTRACK — وزیر را بردار */
        }
    }
    return 0;   /* هیچ ستونی در این ردیف جواب نداد */
}

void print_board(int board[N][N]) {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            printf("%c ", board[i][j] ? 'Q' : '.');
        }
        printf("\n");
    }
}

int main(void) {
    int board[N][N] = {0};

    if (solve_nqueens(board, 0)) {
        printf("یک راه‌حل برای %d وزیر:\n\n", N);
        print_board(board);
    } else {
        printf("راه‌حلی پیدا نشد\n");
    }

    return 0;
}
