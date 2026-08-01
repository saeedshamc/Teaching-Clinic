/*
 * تمرین ۲ — فاصلهٔ دو نقطه با قضیهٔ فیثاغورس
 * نکته: چون از sqrt استفاده می‌کنیم، هنگام کامپایل باید -lm اضافه شود:
 *   gcc -Wall -Wextra -o program exercise_02.c -lm
 */

#include <stdio.h>
#include <math.h>

struct Point {
    double x;
    double y;
};

double distance(struct Point a, struct Point b) {
    double dx = a.x - b.x;
    double dy = a.y - b.y;
    return sqrt(dx * dx + dy * dy);
}

int main(void) {
    struct Point p1 = {0.0, 0.0};
    struct Point p2 = {3.0, 4.0};

    printf("فاصله = %.2f\n", distance(p1, p2));   /* 5 */
    return 0;
}
