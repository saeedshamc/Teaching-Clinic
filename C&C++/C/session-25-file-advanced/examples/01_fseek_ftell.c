/*
 * جلسه ۲۵ — fseek و ftell: دسترسی تصادفی و اندازهٔ فایل
 */

#include <stdio.h>

int main(void) {
    FILE *fp = fopen("sample.txt", "w");
    if (fp == NULL) {
        return 1;
    }
    fprintf(fp, "0123456789\n");   /* ۱۱ بایت */
    fclose(fp);

    fp = fopen("sample.txt", "r");
    if (fp == NULL) {
        return 1;
    }

    /* اندازهٔ فایل: برو به انتها و موقعیت را بخوان */
    fseek(fp, 0, SEEK_END);
    long size = ftell(fp);
    printf("اندازهٔ فایل: %ld بایت\n", size);

    /* برگرد به ابتدا و کاراکتر سوم را بخوان */
    fseek(fp, 0, SEEK_SET);
    fseek(fp, 2, SEEK_SET);   /* برو به بایت ۲ */
    int c = fgetc(fp);
    printf("کاراکتر بایت ۲: %c\n", c);

    fclose(fp);
    return 0;
}
