/*
 * جلسه ۱۰ — فیبوناچی: بازگشتی (ساده ولی کند) و حلقوی (سریع)
 * فیبوناچی: fib(0)=0, fib(1)=1, fib(n)=fib(n-1)+fib(n-2)
 */

#include <stdio.h>

/* نسخهٔ بازگشتی — برای n بزرگ بسیار کند است */
int fib_recursive(int n) {
    if (n <= 1) {
        return n;
    }
    return fib_recursive(n - 1) + fib_recursive(n - 2);
}

/* نسخهٔ حلقوی — سریع و بدون مصرف پشته */
int fib_loop(int n) {
    if (n <= 1) {
        return n;
    }
    int a = 0, b = 1;
    for (int i = 2; i <= n; i++) {
        int next = a + b;
        a = b;
        b = next;
    }
    return b;
}

int main(void) {
    printf("fib(10) بازگشتی = %d\n", fib_recursive(10));
    printf("fib(10) حلقوی   = %d\n", fib_loop(10));
    printf("fib(20) حلقوی   = %d\n", fib_loop(20));
    return 0;
}
