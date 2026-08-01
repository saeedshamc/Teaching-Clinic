/*
 * جلسه ۴ — خواندن ورودی با scanf و چک کردن مقدار بازگشتی
 */

#include <stdio.h>

int main(void) {
    int age = 0;

    printf("سن خود را وارد کنید: ");

    /* scanf تعداد آیتم‌های خوانده‌شده را برمی‌گرداند */
    if (scanf("%d", &age) != 1) {
        printf("خطا: ورودی نامعتبر!\n");
        return 1;
    }

    printf("سال آینده سن شما: %d\n", age + 1);

    /* خواندن یک حرف */
    char grade;
    printf("نمرهٔ خود را وارد کنید (A/B/C): ");
    scanf(" %c", &grade);   /* فاصلهٔ قبل از %c بافر را پاک می‌کند */
    printf("نمرهٔ شما: %c\n", grade);

    return 0;
}
