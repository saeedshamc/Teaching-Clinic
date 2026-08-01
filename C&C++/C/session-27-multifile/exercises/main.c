/*
 * تمرین ۱ — برنامهٔ اصلی
 * کامپایل: gcc -Wall -Wextra -o program main.c utils.c
 */

#include <stdio.h>
#include "utils.h"

int main(void) {
    printf("square(5) = %d\n", square(5));
    printf("cube(3) = %d\n", cube(3));
    return 0;
}
