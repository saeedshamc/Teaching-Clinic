/*
 * تمرین ۲ — شمارش تعداد یک حرف مشخص در رشته
 */

#include <stdio.h>

int main(void) {
    char text[200];

    printf("یک جمله وارد کنید: ");
    if (fgets(text, sizeof(text), stdin) == NULL) {
        return 1;
    }

    /* شمارش حرف 'a' (بدون حساسیت به بزرگی/کوچکی) */
    int count = 0;
    for (int i = 0; text[i] != '\0'; i++) {
        char c = text[i];
        if (c == 'a' || c == 'A') {
            count++;
        }
    }

    printf("تعداد حرف a: %d\n", count);
    return 0;
}
