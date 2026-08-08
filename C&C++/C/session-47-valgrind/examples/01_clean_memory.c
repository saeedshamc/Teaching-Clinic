/*
 * جلسه ۴۷ — برنامهٔ سالم از نظر valgrind
 *
 * اجرا:
 *   gcc -Wall -Wextra -g -o 01_clean_memory 01_clean_memory.c
 *   valgrind --leak-check=full ./01_clean_memory
 *
 * خروجی باید در انتها داشته باشد:
 *   All heap blocks were freed -- no leaks are possible
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int n = 5;
    int *arr = (int *)malloc((size_t)n * sizeof(int));

    if (arr == NULL) {
        fprintf(stderr, "خطای تخصیص حافظه\n");
        return 1;
    }

    for (int i = 0; i < n; i++) {
        arr[i] = i * i;
    }

    printf("آرایهٔ پویا:\n");
    for (int i = 0; i < n; i++) {
        printf("  arr[%d] = %d\n", i, arr[i]);
    }

    free(arr);        /* آزاد کردن — بدون این خط، valgrind نشت گزارش می‌دهد */
    arr = NULL;       /* جلوگیری از dangling pointer */

    return 0;
}
