/*
 * تمرین ۱ — اندازهٔ فایل با fseek/ftell
 * TODO: فایلی بسازید، چند بایت در آن بنویسید و اندازه را با ftell چاپ کنید.
 */

#include <stdio.h>

int main(void) {
    FILE *fp = fopen("size_test.txt", "w");
    if (fp == NULL) {
        return 1;
    }
    fprintf(fp, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");   /* ۲۶ حرف */
    fclose(fp);

    fp = fopen("size_test.txt", "r");
    if (fp == NULL) {
        return 1;
    }

    fseek(fp, 0, SEEK_END);
    long size = ftell(fp);
    printf("اندازهٔ فایل: %ld بایت\n", size);

    fclose(fp);
    return 0;
}
