/*
 * جلسه ۳۳ — توابع حافظهٔ عمومی: memcpy و memset
 */

#include <stdio.h>
#include <string.h>

int main(void) {
    int src[5] = {1, 2, 3, 4, 5};
    int dst[5];

    /* کپی بدون توجه به نوع */
    memcpy(dst, src, sizeof(src));
    printf("بعد از memcpy: ");
    for (int i = 0; i < 5; i++) {
        printf("%d ", dst[i]);
    }
    printf("\n");

    /* پر کردن با مقدار */
    int zeros[5];
    memset(zeros, 0, sizeof(zeros));
    printf("بعد از memset با صفر: ");
    for (int i = 0; i < 5; i++) {
        printf("%d ", zeros[i]);
    }
    printf("\n");

    /* memcmp */
    int a[3] = {1, 2, 3};
    int b[3] = {1, 2, 4};
    int cmp = memcmp(a, b, sizeof(a));
    printf("memcmp(a, b) = %d (غیرصفر یعنی متفاوت)\n", cmp);

    return 0;
}
