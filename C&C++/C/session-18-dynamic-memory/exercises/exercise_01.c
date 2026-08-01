/*
 * تمرین ۱ — آرایهٔ پویا با malloc
 * TODO: آرایه‌ای با malloc بسازید، مقداردهی کنید، چاپ و در پایان free کنید.
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int n = 5;
    int *arr = malloc((size_t)n * sizeof(int));

    if (arr == NULL) {
        printf("تخصیص حافظه ناموفق!\n");
        return 1;
    }

    /* TODO: عناصر را مقداردهی کنید (مثلاً arr[i] = i * 10) */
    for (int i = 0; i < n; i++) {
        arr[i] = i * 10;
    }

    /* TODO: چاپ کنید */
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    free(arr);   /* مهم: آزادسازی */
    return 0;
}
