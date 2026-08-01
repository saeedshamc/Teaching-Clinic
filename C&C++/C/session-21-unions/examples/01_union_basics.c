/*
 * جلسه ۲۱ — union: حافظهٔ مشترک
 */

#include <stdio.h>

union Number {
    int i;
    float f;
};

int main(void) {
    union Number n;

    printf("اندازهٔ union Number: %zu بایت (بزرگ‌ترین عضو)\n", sizeof(union Number));
    printf("اندازهٔ int: %zu، اندازهٔ float: %zu\n", sizeof(int), sizeof(float));

    n.i = 42;
    printf("n.i = %d\n", n.i);

    n.f = 3.14f;
    /* حالا n.i دیگر ۴۲ نیست! چون فضا مشترک است */
    printf("بعد از نوشتن n.f، مقدار n.i: %d (خرب شده!)\n", n.i);
    printf("n.f = %f\n", n.f);

    return 0;
}
