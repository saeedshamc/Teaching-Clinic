/*
 * جلسه ۴۶ — برنامهٔ نمونه برای تمرین دیباگ با gdb
 *
 * کامپایل با پرچم دیباگ:
 *   gcc -Wall -Wextra -g -o 01_gdb_target 01_gdb_target.c
 * سپس:
 *   gdb ./01_gdb_target
 *
 * دستورهای پیشنهادی در gdb:
 *   break main
 *   run
 *   next
 *   print n
 *   step          -> وارد تابع factorial می‌شود
 *   print i
 *   continue
 */

#include <stdio.h>

int factorial(int n) {
    int result = 1;
    for (int i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}

int sum_to(int n) {
    int total = 0;
    for (int i = 1; i <= n; i++) {
        total += i;
    }
    return total;
}

int main(void) {
    int n = 6;

    printf("فاکتوریل %d = %d\n", n, factorial(n));
    printf("جمع ۱ تا %d = %d\n", n, sum_to(n));

    return 0;
}
