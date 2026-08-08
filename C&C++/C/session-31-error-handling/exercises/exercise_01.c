/*
 * تمرین ۱ — مدیریت خطای باز کردن فایل
 * TODO: فایلی را باز کنید که وجود ندارد و خطا را با perror و strerror چاپ کنید.
 */

#include <stdio.h>
#include <errno.h>
#include <string.h>

int main(void) {
    errno = 0;
    FILE *fp = fopen("not_here.txt", "r");
    if (fp == NULL) {
        perror("باز کردن فایل");
        printf("strerror: %s\n", strerror(errno));
        return 1;
    }
    fclose(fp);
    return 0;
}
