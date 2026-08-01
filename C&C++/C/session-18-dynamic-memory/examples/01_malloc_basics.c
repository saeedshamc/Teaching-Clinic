/*
 * جلسه ۱۸ — malloc و free
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int n = 5;

    /* تخصیص ۵ عدد صحیح در هیپ */
    int *arr = malloc((size_t)n * sizeof(int));

    /* همیشه NULL را چک کن */
    if (arr == NULL) {
        printf("تخصیص حافظه ناموفق!\n");
        return 1;
    }

    /* مقداردهی و چاپ */
    for (int i = 0; i < n; i++) {
        arr[i] = (i + 1) * 10;
    }
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    /* آزادسازی — فراموشش نکن */
    free(arr);
    arr = NULL;   /* دیگر استفاده نکن */

    return 0;
}
