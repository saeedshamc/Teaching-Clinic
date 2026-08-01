/*
 * جلسه ۱۸ — calloc (صفرشده) و realloc (تغییر اندازه)
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
    /* calloc: ۵ عنصر صفرشده */
    int *arr = calloc(5, sizeof(int));
    if (arr == NULL) {
        printf("تخصیص ناموفق!\n");
        return 1;
    }

    printf("بعد از calloc (همه صفرند): ");
    for (int i = 0; i < 5; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    /* مقداردهی */
    for (int i = 0; i < 5; i++) {
        arr[i] = i * 2;
    }

    /* realloc: بزرگ‌تر کردن به ۱۰ عنصر */
    int *bigger = realloc(arr, 10 * sizeof(int));
    if (bigger == NULL) {
        printf("realloc ناموفق!\n");
        free(arr);
        return 1;
    }
    arr = bigger;

    for (int i = 5; i < 10; i++) {
        arr[i] = i * 2;
    }

    printf("بعد از realloc: ");
    for (int i = 0; i < 10; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    free(arr);
    return 0;
}
