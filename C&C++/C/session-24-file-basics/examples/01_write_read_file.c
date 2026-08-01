/*
 * جلسه ۲۴ — نوشتن و خواندن فایل متنی
 */

#include <stdio.h>

int main(void) {
    FILE *fp;

    /* ۱) نوشتن */
    fp = fopen("output.txt", "w");
    if (fp == NULL) {
        printf("خطا در باز کردن فایل برای نوشتن!\n");
        return 1;
    }

    fprintf(fp, "خط اول: سلام\n");
    fprintf(fp, "خط دوم: این یک فایل متنی است.\n");
    fprintf(fp, "عدد: %d\n", 42);

    fclose(fp);
    printf("فایل نوشته شد.\n");

    /* ۲) خواندن */
    fp = fopen("output.txt", "r");
    if (fp == NULL) {
        printf("خطا در باز کردن فایل برای خواندن!\n");
        return 1;
    }

    char line[256];
    printf("محتویات فایل:\n");
    while (fgets(line, sizeof(line), fp) != NULL) {
        printf("%s", line);
    }

    fclose(fp);
    return 0;
}
