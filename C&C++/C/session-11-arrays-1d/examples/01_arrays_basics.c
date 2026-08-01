/*
 * جلسه ۱۱ — تعریف، مقداردهی و پیمایش آرایه
 */

#include <stdio.h>

#define SIZE 5

int main(void) {
    /* مقداردهی اولیه */
    int scores[SIZE] = {90, 85, 78, 92, 88};

    /* دسترسی مستقیم */
    printf("اولین نمره: %d\n", scores[0]);
    printf("آخرین نمره: %d\n", scores[SIZE - 1]);

    /* تغییر یک عنصر */
    scores[2] = 80;
    printf("نمرهٔ سوم بعد از تغییر: %d\n", scores[2]);

    /* پیمایش */
    printf("همهٔ نمره‌ها: ");
    for (int i = 0; i < SIZE; i++) {
        printf("%d ", scores[i]);
    }
    printf("\n");

    /* تعداد عناصر با sizeof (فقط در همان اسکوپ تعریف) */
    printf("تعداد عناصر: %zu\n", sizeof(scores) / sizeof(scores[0]));

    return 0;
}
