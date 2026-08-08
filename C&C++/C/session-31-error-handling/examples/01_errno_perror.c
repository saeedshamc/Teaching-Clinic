/*
 * جلسه ۳۱ — errno و perror برای مدیریت خطای سیستم
 */

#include <stdio.h>
#include <errno.h>
#include <string.h>   /* برای strerror */

int main(void) {
    /* تلاش برای باز کردن فایلی که وجود ندارد */
    errno = 0;   /* قبل از فراخوانی صفرش کن */

    FILE *fp = fopen("file_that_does_not_exist.txt", "r");
    if (fp == NULL) {
        printf("errno = %d\n", errno);
        perror("پیام ما (perror)");              /* روی stderr */
        printf("strerror: %s\n", strerror(errno));
        return 1;
    }

    fclose(fp);
    return 0;
}
