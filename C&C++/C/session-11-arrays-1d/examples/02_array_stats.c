/*
 * جلسه ۱۱ — مجموع، میانگین و بیشینهٔ آرایه
 */

#include <stdio.h>

#define SIZE 6

int main(void) {
    int numbers[SIZE] = {12, 45, 3, 67, 21, 9};

    int sum = 0;
    int max = numbers[0];

    for (int i = 0; i < SIZE; i++) {
        sum += numbers[i];
        if (numbers[i] > max) {
            max = numbers[i];
        }
    }

    double average = (double)sum / SIZE;   /* تبدیل به double برای تقسیم اعشاری */
    printf("مجموع: %d\n", sum);
    printf("میانگین: %.2f\n", average);
    printf("بیشینه: %d\n", max);

    return 0;
}
