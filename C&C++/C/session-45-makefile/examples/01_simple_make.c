/*
 * جلسه ۴۵ — برنامهٔ سادهٔ نمونه برای ساخت با make
 *
 * اجرا:
 *   make            -> ساخت خروجی
 *   make run        -> اجرا
 *   make clean      -> حذف فایل‌های میانی
 */

#include <stdio.h>

int factorial(int n) {
    int result = 1;
    for (int i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}

int main(void) {
    for (int i = 1; i <= 7; i++) {
        printf("%d! = %d\n", i, factorial(i));
    }
    return 0;
}
