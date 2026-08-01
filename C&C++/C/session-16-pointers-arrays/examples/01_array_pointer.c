/*
 * جلسه ۱۶ — رابطهٔ آرایه و اشاره‌گر
 */

#include <stdio.h>

int main(void) {
    int arr[5] = {10, 20, 30, 40, 50};
    int *p = arr;   /* p به عنصر اول اشاره می‌کند */

    /* معادل‌بودن ایندکس و اشاره‌گر */
    printf("arr[2] = %d\n", arr[2]);
    printf("*(arr + 2) = %d\n", *(arr + 2));
    printf("p[2] = %d\n", p[2]);
    printf("*(p + 2) = %d\n", *(p + 2));

    /* پیمایش با اشاره‌گر */
    printf("پیمایش با اشاره‌گر: ");
    for (int *q = arr; q < arr + 5; q++) {
        printf("%d ", *q);
    }
    printf("\n");

    /* sizeof: آرایه در جای تعریف، کل را می‌دهد */
    printf("sizeof(arr) = %zu بایت\n", sizeof(arr));
    printf("sizeof(p) = %zu بایت\n", sizeof(p));

    return 0;
}
