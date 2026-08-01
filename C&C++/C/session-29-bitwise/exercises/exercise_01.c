/*
 * تمرین ۱ — ضرب و تقسیم با شیفت
 */

#include <stdio.h>

int main(void) {
    unsigned int n = 5;

    printf("%u << 3 = %u (5×8)\n", n, n << 3);
    printf("40 >> 2 = %u (40÷4)\n", (n << 3) >> 2);
    return 0;
}
