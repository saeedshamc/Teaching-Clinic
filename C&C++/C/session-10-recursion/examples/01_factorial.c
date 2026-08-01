/*
 * جلسه ۱۰ — فاکتوریل بازگشتی
 */

#include <stdio.h>

/* فاکتوریل: n! = n * (n-1)! ، با پایهٔ 0! = 1 */
int factorial(int n) {
    if (n <= 1) {
        return 1;              /* شرط پایه */
    }
    return n * factorial(n - 1);   /* شرط بازگشتی */
}

int main(void) {
    for (int i = 0; i <= 10; i++) {
        printf("%d! = %d\n", i, factorial(i));
    }
    return 0;
}
