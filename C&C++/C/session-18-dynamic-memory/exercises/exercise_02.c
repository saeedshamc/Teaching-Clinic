/*
 * تمرین ۲ — آرایهٔ پویا با اندازهٔ متغیر و realloc
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int n;

    printf("تعداد عناصر: ");
    if (scanf("%d", &n) != 1 || n <= 0) {
        printf("ورودی نامعتبر!\n");
        return 1;
    }

    int *arr = malloc((size_t)n * sizeof(int));
    if (arr == NULL) {
        printf("تخصیص ناموفق!\n");
        return 1;
    }

    for (int i = 0; i < n; i++) {
        arr[i] = i + 1;
    }

    /* دو برابر کردن */
    int new_size = n * 2;
    int *bigger = realloc(arr, (size_t)new_size * sizeof(int));
    if (bigger == NULL) {
        free(arr);
        return 1;
    }
    arr = bigger;

    for (int i = n; i < new_size; i++) {
        arr[i] = i + 1;
    }

    for (int i = 0; i < new_size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    free(arr);
    return 0;
}
