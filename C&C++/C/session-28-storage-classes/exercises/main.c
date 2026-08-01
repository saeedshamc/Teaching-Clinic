/*
 * تمرین ۲ — استفاده از extern
 * کامپایل: gcc -Wall -Wextra -o program main.c globals.c
 */

#include <stdio.h>
#include "globals.h"

int main(void) {
    increment_counter();
    increment_counter();
    increment_counter();

    printf("شمارنده: %d\n", get_counter());

    /* دسترسی مستقیم به متغیر سراسری از طریق اعلان هدر */
    printf("مقدار مستقیم: %d\n", global_counter);

    return 0;
}
