/*
 * جلسه ۹ — گرفتن نتیجه با مقدار برگشتی (return)
 */

#include <stdio.h>

/* به‌جای تغییر ورودی، مقدار جدید برمی‌گردانیم */
int increment(int x) {
    return x + 1;
}

/* مثال واقعی‌تر: محاسبهٔ فاکتوریل ساده با حلقه */
int factorial(int n) {
    int result = 1;
    for (int i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}

int main(void) {
    int n = 5;
    n = increment(n);           /* نتیجه را بگیر */
    printf("n بعد از increment = %d\n", n);

    printf("factorial(5) = %d\n", factorial(5));
    printf("factorial(7) = %d\n", factorial(7));
    return 0;
}
