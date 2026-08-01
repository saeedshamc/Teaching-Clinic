/*
 * تمرین ۱ — چهار عمل اصلی روی دو عدد
 * TODO: دو عدد صحیح بگیرید و جمع، تفریق، ضرب و تقسیم را چاپ کنید.
 */

#include <stdio.h>

int main(void) {
    int a, b;

    printf("عدد اول: ");
    if (scanf("%d", &a) != 1) {
        printf("ورودی نامعتبر!\n");
        return 1;
    }
    printf("عدد دوم: ");
    if (scanf("%d", &b) != 1) {
        printf("ورودی نامعتبر!\n");
        return 1;
    }

    /* TODO: خروجی‌ها را کامل کنید */
    printf("جمع: %d\n", a + b);
    printf("تفریق: %d\n", a - b);
    printf("ضرب: %d\n", a * b);
    if (b != 0) {
        printf("تقسیم: %d\n", a / b);
    } else {
        printf("تقسیم بر صفر ممکن نیست!\n");
    }

    return 0;
}
