/*
 * تمرین ۱ — برنامهٔ ساده برای ساخت با Makefile اختصاصی خودتان
 *
 * ۱) این فایل را کپی کنید و یک Makefile کنارش بسازید با:
 *    - هدف all: ساخت خروجی exercise_01
 *    - هدف run: اجرای برنامه
 *    - هدف clean: حذف فایل‌های میانی
 * ۲) از متغیر CC و CFLAGS استفاده کنید.
 */

#include <stdio.h>

int sum_to(int n) {
    int sum = 0;
    for (int i = 1; i <= n; i++) {
        sum += i;
    }
    return sum;
}

int main(void) {
    int n = 100;
    printf("جمع اعداد ۱ تا %d = %d\n", n, sum_to(n));
    return 0;
}
