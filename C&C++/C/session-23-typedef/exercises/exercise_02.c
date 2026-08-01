/*
 * تمرین ۲ — Meter و Second و محاسبهٔ سرعت
 */

#include <stdio.h>

typedef double Meter;
typedef double Second;

int main(void) {
    Meter distance = 100.0;    /* متر */
    Second time = 9.58;        /* ثانیه */

    double speed = distance / time;   /* متر بر ثانیه */
    printf("سرعت = %.2f m/s\n", speed);
    return 0;
}
