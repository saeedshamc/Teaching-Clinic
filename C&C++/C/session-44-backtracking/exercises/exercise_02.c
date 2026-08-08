/*
 * تمرین ۲ — پیدا کردن مسیر در maze ۵×۵ و چاپ مسیر با * روی نقشه
 */

#include <stdio.h>

#define ROWS 5
#define COLS 5

int maze[ROWS][COLS] = {
    {1, 0, 1, 0, 1},
    {1, 1, 1, 0, 1},
    {0, 1, 0, 1, 1},
    {0, 1, 1, 1, 0},
    {1, 1, 0, 1, 1}
};

/* راه‌حل نهایی روی خود maze ذخیره می‌شود: 2 = خانهٔ مسیر */
int solve_maze(int r, int c) {
    if (r < 0 || r >= ROWS || c < 0 || c >= COLS) {
        return 0;
    }
    if (maze[r][c] != 1) {   /* دیوار یا قبلاً از مسیر رد شده */
        return 0;
    }

    if (r == ROWS - 1 && c == COLS - 1) {
        maze[r][c] = 2;
        return 1;
    }

    maze[r][c] = 2;   /* علامت مسیر */

    if (solve_maze(r + 1, c)) return 1;
    if (solve_maze(r, c + 1)) return 1;
    if (solve_maze(r - 1, c)) return 1;
    if (solve_maze(r, c - 1)) return 1;

    maze[r][c] = 1;   /* این راه به مقصد نرسید؛ علامت را بردار */
    return 0;
}

int main(void) {
    if (solve_maze(0, 0)) {
        printf("نقشه با مسیر (* = مسیر، # = دیوار):\n\n");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (maze[i][j] == 2) {
                    printf("* ");
                } else if (maze[i][j] == 0) {
                    printf("# ");
                } else {
                    printf(". ");
                }
            }
            printf("\n");
        }
    } else {
        printf("راهی به مقصد نیست\n");
    }

    return 0;
}
