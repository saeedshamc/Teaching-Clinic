/*
 * تمرین ۱ — typedef برای struct Point
 */

#include <stdio.h>

typedef struct {
    double x;
    double y;
} Point;

int main(void) {
    Point p1 = {1.0, 2.0};
    Point p2 = {3.5, 4.5};

    printf("p1 = (%.1f, %.1f)\n", p1.x, p1.y);
    printf("p2 = (%.1f, %.1f)\n", p2.x, p2.y);
    return 0;
}
