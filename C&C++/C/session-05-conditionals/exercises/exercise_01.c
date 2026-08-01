/*
 * تمرین ۱ — تشخیص مثبت/منفی/صفر
 * TODO: این برنامه را کامل کنید تا بگوید عدد مثبت، منفی یا صفر است.
 */

#include <stdio.h>

int main(void) {
    int n;

    printf("عدد: ");
    if (scanf("%d", &n) != 1) {
        printf("ورودی نامعتبر!\n");
        return 1;
    }

    /* TODO: شرط‌ها را بنویسید */
    if (n > 0) {
        printf("مثبت\n");
    } else if (n < 0) {
        printf("منفی\n");
    } else {
        printf("صفر\n");
    }

    return 0;
}
