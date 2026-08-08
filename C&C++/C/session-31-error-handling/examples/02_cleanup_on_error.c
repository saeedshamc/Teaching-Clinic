/*
 * جلسه ۳۱ — الگوی goto cleanup برای آزادسازی منابع هنگام خطا
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
    FILE *fp = NULL;
    int *data = NULL;

    /* مرحله ۱: تخصیص حافظه */
    data = malloc(100 * sizeof(int));
    if (data == NULL) {
        perror("malloc");
        goto cleanup;
    }

    /* مرحله ۲: باز کردن فایل */
    fp = fopen("data_out.txt", "w");
    if (fp == NULL) {
        perror("fopen");
        goto cleanup;
    }

    /* مرحله ۳: کار اصلی */
    for (int i = 0; i < 100; i++) {
        data[i] = i * i;
    }
    fwrite(data, sizeof(int), 100, fp);
    printf("داده نوشته شد.\n");

cleanup:
    /* همیشه همین‌جا منابع آزاد می‌شوند — چه خطا چه موفق */
    if (fp != NULL) {
        fclose(fp);
    }
    free(data);
    printf("پایان (منابع آزاد شد).\n");

    return 0;
}
