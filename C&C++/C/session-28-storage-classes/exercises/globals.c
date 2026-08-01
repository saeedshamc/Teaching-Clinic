/*
 * تمرین ۲ — تعریف متغیر سراسری و توابع
 */

#include "globals.h"

/* تعریف واقعی متغیر سراسری — فقط اینجا یک بار */
int global_counter = 0;

int increment_counter(void) {
    global_counter++;
    return global_counter;
}

int get_counter(void) {
    return global_counter;
}
