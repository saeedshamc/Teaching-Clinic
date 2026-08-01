/*
 * تمرین ۲ — پیدا کردن بیشینه و کمینه
 */

#include <stdio.h>

#define SIZE 10

int main(void) {
    int numbers[SIZE];

    printf("۱۰ عدد وارد کنید:\n");
    for (int i = 0; i < SIZE; i++) {
        if (scanf("%d", &numbers[i]) != 1) {
            printf("ورودی نامعتبر!\n");
            return 1;
        }
    }

    int max = numbers[0];
    int min = numbers[0];
    for (int i = 1; i < SIZE; i++) {
        if (numbers[i] > max) {
            max = numbers[i];
        }
        if (numbers[i] < min) {
            min = numbers[i];
        }
    }

    printf("بیشینه = %d\n", max);
    printf("کمینه = %d\n", min);
    return 0;
}
