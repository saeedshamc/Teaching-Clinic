/*
 * تمرین ۲ — پیدا کردن محل crash با backtrace
 *
 * برنامه یک باگ دارد: در تابع get_element به آرایه دسترسی نامناسب
 * می‌شود. با gdb اجرا کنید:
 *   gcc -Wall -Wextra -g -o exercise_02 exercise_02.c
 *   gdb ./exercise_02
 *   (gdb) run
 *   (gdb) bt
 * و باگ را پیدا کنید.
 */

#include <stdio.h>

int get_element(int arr[], int size, int index) {
    (void)size;   /* جلوگیری از هشدار unused parameter با -Wall -Wextra */

    /* باگ عمدی: اندازهٔ بافر را در نظر نگرفته است */
    return arr[index];
}

int main(void) {
    int numbers[] = {10, 20, 30, 40};
    int size = (int)(sizeof(numbers) / sizeof(numbers[0]));

    printf("عنصر ۲: %d\n", get_element(numbers, size, 2));
    printf("عنصر ۱۰: %d\n", get_element(numbers, size, 10));   /* خارج از محدوده */

    return 0;
}
