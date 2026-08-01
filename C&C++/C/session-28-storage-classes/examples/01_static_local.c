/*
 * جلسه ۲۸ — static محلی: حالت ماندگار بین صدا زدن‌ها
 */

#include <stdio.h>

void counter(void) {
    static int count = 0;   /* فقط بار اول ۰ می‌شود */
    count++;
    printf("%d ", count);
}

void normal(void) {
    int n = 0;              /* هر بار از نو ۰ می‌شود */
    n++;
    printf("%d ", n);
}

int main(void) {
    printf("static: ");
    for (int i = 0; i < 5; i++) {
        counter();
    }
    printf("\n");

    printf("normal: ");
    for (int i = 0; i < 5; i++) {
        normal();
    }
    printf("\n");

    return 0;
}
