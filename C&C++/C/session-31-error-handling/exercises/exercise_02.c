/*
 * تمرین ۲ — تنظیم errno برای ورودی نامعتبر
 */

#include <stdio.h>
#include <errno.h>
#include <string.h>

/* ریشهٔ دوم ساده — اگر ورودی منفی بود errno = EINVAL */
double my_sqrt(double x) {
    if (x < 0) {
        errno = EINVAL;   /* آرگومان نامعتبر */
        return -1.0;
    }
    return x * x;   /* برای سادگی، مربع! */
}

int main(void) {
    double r = my_sqrt(-4.0);
    if (r < 0) {
        printf("خطا: %s (errno=%d)\n", strerror(errno), errno);
    } else {
        printf("نتیجه: %f\n", r);
    }
    return 0;
}
