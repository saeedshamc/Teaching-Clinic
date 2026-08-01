/*
 * جلسه ۳۰ — جمع دو عدد از خط فرمان با مدیریت خطا
 * اجرا: ./program 12 30
 */

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
    if (argc != 3) {
        printf("استفاده: %s <عدد۱> <عدد۲>\n", argv[0]);
        return 1;
    }

    /* atoi: رشته → عدد صحیح */
    int a = atoi(argv[1]);
    int b = atoi(argv[2]);

    printf("%d + %d = %d\n", a, b, a + b);
    return 0;
}
