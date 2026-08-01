/*
 * تمرین ۲ — توان بازگشتی: base^exp
 * power(2, 5) = 2 * power(2, 4) = ... = 32
 */

#include <stdio.h>

int power(int base, int exp) {
    if (exp == 0) {
        return 1;              /* شرط پایه: هر عدد به توان صفر = ۱ */
    }
    return base * power(base, exp - 1);
}

int main(void) {
    printf("2^5 = %d\n", power(2, 5));
    printf("3^4 = %d\n", power(3, 4));
    printf("7^0 = %d\n", power(7, 0));
    return 0;
}
