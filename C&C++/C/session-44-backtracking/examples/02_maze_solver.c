/*
 * جلسه ۴۴ — پیدا کردن مسیر در لابیرنت (maze) با Backtracking
 * 1 = مسیر باز، 0 = دیوار
 */

#include <stdio.h>

#define ROWS 5
#define COLS 5

int maze[ROWS][COLS] = {
    {1, 0, 1, 1, 1},
    {1, 0, 1, 0, 1},
    {1, 1, 1, 0, 1},
    {0, 0, 1, 0, 1},
    {1, 1, 1, 0, 1}
};

int visited[ROWS][COLS] = {0};

/* شروع از (0,0) و رسیدن به (ROWS-1, COLS-1) */
int solve_maze(int r, int c) {
    /* خارج از محدوده یا دیوار یا قبلاً دیده شده؟ */
    if (r < 0 || r >= ROWS || c < 0 || c >= COLS) {
        return 0;
    }
    if (maze[r][c] == 0 || visited[r][c]) {
        return 0;
    }

    /* به مقصد رسیدیم؟ */
    if (r == ROWS - 1 && c == COLS - 1) {
        return 1;
    }

    visited[r][c] = 1;   /* این خانه را علامت بزن */

    /* چهار جهت را امتحان کن */
    if (solve_maze(r + 1, c)) return 1;   /* پایین */
    if (solve_maze(r - 1, c)) return 1;   /* بالا */
    if (solve_maze(r, c + 1)) return 1;   /* راست */
    if (solve_maze(r, c - 1)) return 1;   /* چپ */

    return 0;   /* بن‌بست */
}

void print_visited(void) {
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            printf("%c ", visited[i][j] ? '*' : (maze[i][j] ? '.' : '#'));
        }
        printf("\n");
    }
}

int main(void) {
    if (solve_maze(0, 0)) {
        printf("مسیر پیدا شد (ستاره‌ها = خانه‌های پیموده‌شده):\n\n");
        print_visited();
    } else {
        printf("راهی به مقصد نیست\n");
    }

    return 0;
}
