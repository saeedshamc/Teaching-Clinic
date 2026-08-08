/*
 * تمرین ۲ — تابع جنریک swap با memcpy
 */

#include <stdio.h>
#include <string.h>

/* دو داده با اندازهٔ دلخواه را جابه‌جا می‌کند */
void swap_generic(void *a, void *b, size_t size) {
    unsigned char temp[64];
    memcpy(temp, a, size);
    memcpy(a, b, size);
    memcpy(b, temp, size);
}

int main(void) {
    int x = 10, y = 20;
    printf("قبل: x=%d, y=%d\n", x, y);
    swap_generic(&x, &y, sizeof(int));
    printf("بعد: x=%d, y=%d\n", x, y);

    double p = 1.5, q = 9.9;
    printf("قبل: p=%.1f, q=%.1f\n", p, q);
    swap_generic(&p, &q, sizeof(double));
    printf("بعد: p=%.1f, q=%.1f\n", p, q);

    return 0;
}
