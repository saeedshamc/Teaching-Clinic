/*
 * تمرین ۲ — جدول ضرب ۱ تا ۱۰ با حلقهٔ تودرتو
 */

#include <stdio.h>

int main(void) {
    for (int i = 1; i <= 10; i++) {
        for (int j = 1; j <= 10; j++) {
            printf("%4d", i * j);   /* عرض ۴ برای هم‌ترازی */
        }
        printf("\n");
    }
    return 0;
}
