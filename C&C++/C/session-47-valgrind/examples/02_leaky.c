/*
 * جلسه ۴۷ — برنامهٔ عمداً نشت‌دار برای تمرین با valgrind
 *
 * این برنامه یک نشت حافظه دارد (free فراموش شده است).
 * با valgrind آن را پیدا کنید:
 *   gcc -Wall -Wextra -g -o 02_leaky 02_leaky.c
 *   valgrind --leak-check=full ./02_leaky
 *
 * خروجی valgrind باید در «LEAK SUMMARY» نشان دهد که
 * ۴۰ بایت در تابع leak_demo نشت شده است.
 */

#include <stdio.h>
#include <stdlib.h>

void leak_demo(void) {
    int *p = (int *)malloc(10 * sizeof(int));

    if (p == NULL) {
        fprintf(stderr, "خطای تخصیص حافظه\n");
        return;
    }

    for (int i = 0; i < 10; i++) {
        p[i] = i;
    }

    /* باگ عمدی: free(p) فراموش شده است! */
}

int main(void) {
    leak_demo();
    printf("پایان برنامه\n");
    return 0;
}
