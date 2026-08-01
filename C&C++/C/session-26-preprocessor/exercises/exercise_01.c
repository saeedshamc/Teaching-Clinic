/*
 * تمرین ۱ — مساحت دایره با #define
 */

#include <stdio.h>

#define PI 3.14159
#define RADIUS 5.0

int main(void) {
    double area = PI * RADIUS * RADIUS;
    printf("مساحت دایره با شعاع %.1f = %.2f\n", RADIUS, area);
    return 0;
}
