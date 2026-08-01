/*
 * جلسه ۱۵ — null pointer و رفتار ایمن
 */

#include <stdio.h>

void safe_print(int *p) {
    if (p == NULL) {
        printf("اشاره‌گر NULL است!\n");
        return;
    }
    printf("مقدار: %d\n", *p);
}

int main(void) {
    int value = 7;
    int *p1 = &value;    /* درست */
    int *p2 = NULL;      /* اشاره‌گر خالی */

    safe_print(p1);
    safe_print(p2);      /* بدون کرش */

    return 0;
}
