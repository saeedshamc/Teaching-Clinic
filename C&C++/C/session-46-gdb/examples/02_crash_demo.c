/*
 * جلسه ۴۶ — برنامهٔ عمداً خراب برای پیدا کردن crash با backtrace
 *
 * این برنامه ایندکس خارج از محدودهٔ آرایه را می‌نویسد (باگ عمدی).
 * با gdb اجرا کنید:
 *   gcc -Wall -Wextra -g -o 02_crash_demo 02_crash_demo.c
 *   gdb ./02_crash_demo
 *   (gdb) run
 *   (gdb) bt
 * gdb نشان می‌دهد crash در کدام خط رخ داده است.
 */

#include <stdio.h>

void fill_array(int arr[], int size) {
    /* باگ عمدی: حلقه تا size+5 می‌رود و خارج از آرایه می‌نویسد */
    for (int i = 0; i < size + 5; i++) {
        arr[i] = i * 10;
    }
}

int main(void) {
    int data[5] = {0};

    fill_array(data, 5);

    for (int i = 0; i < 5; i++) {
        printf("data[%d] = %d\n", i, data[i]);
    }

    return 0;
}
