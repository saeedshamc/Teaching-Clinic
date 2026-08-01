/*
 * تمرین ۱ — مجموع و میانگین ۵ عدد
 * TODO: ۵ عدد از کاربر بگیرید (scanf در حلقه) و مجموع و میانگین را چاپ کنید.
 */

#include <stdio.h>

#define SIZE 5

int main(void) {
    int numbers[SIZE];
    int sum = 0;

    printf("۵ عدد وارد کنید:\n");
    for (int i = 0; i < SIZE; i++) {
        if (scanf("%d", &numbers[i]) != 1) {
            printf("ورودی نامعتبر!\n");
            return 1;
        }
        sum += numbers[i];
    }

    double average = (double)sum / SIZE;
    printf("مجموع = %d\n", sum);
    printf("میانگین = %.2f\n", average);
    return 0;
}
