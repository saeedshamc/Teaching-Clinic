/*
 * تمرین ۲ — تشخیص زوج/فرد و فیلتر اعداد
 */

#include <stdio.h>

/* ۱ برمی‌گرداند اگر n زوج است */
int is_even(int n) {
    return n % 2 == 0;
}

int main(void) {
    printf("اعداد زوج ۱ تا ۱۰: ");
    for (int i = 1; i <= 10; i++) {
        if (is_even(i)) {
            printf("%d ", i);
        }
    }
    printf("\n");
    return 0;
}
