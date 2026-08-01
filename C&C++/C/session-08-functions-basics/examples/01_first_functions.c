/*
 * جلسه ۸ — اولین توابع
 */

#include <stdio.h>

/* تابعی که دو عدد را جمع می‌کند */
int add(int a, int b) {
    return a + b;
}

/* تابع بدون ورودی و بدون خروجی */
void say_hello(void) {
    printf("سلام! به جلسهٔ توابع خوش آمدید.\n");
}

int main(void) {
    say_hello();

    int x = add(10, 20);
    printf("add(10, 20) = %d\n", x);
    printf("add(5, 7) = %d\n", add(5, 7));

    return 0;
}
