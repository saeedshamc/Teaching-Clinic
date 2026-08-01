/*
 * تمرین ۱ — شمارنده با static
 * TODO: با static محلی شمارنده بسازید و ۵ بار صدا بزنید.
 * خروجی باید 1 2 3 4 5 باشد.
 */

#include <stdio.h>

void counter(void) {
    static int count = 0;   /* فقط بار اول صفر می‌شود */
    count++;
    printf("%d ", count);
}

int main(void) {
    for (int i = 0; i < 5; i++) {
        counter();
    }
    printf("\n");
    return 0;
}
