/*
 * تمرین ۱ — دیباگ تابع sum با gdb
 *
 * مراحل:
 *   gcc -Wall -Wextra -g -o exercise_01 exercise_01.c
 *   gdb ./exercise_01
 *   (gdb) break sum
 *   (gdb) run
 *   (gdb) next          -> چند بار
 *   (gdb) print total
 *   (gdb) continue
 */

#include <stdio.h>

int sum(int n) {
    int total = 0;
    for (int i = 1; i <= n; i++) {
        total += i;
    }
    return total;
}

int main(void) {
    int n = 10;
    printf("جمع = %d\n", sum(n));
    return 0;
}
