/*
 * تمرین ۲ — مجموع و میانگین از طریق خروجی اشاره‌گری
 */

#include <stdio.h>

void stats(const int *arr, int size, int *sum, double *avg) {
    int total = 0;
    for (int i = 0; i < size; i++) {
        total += arr[i];
    }
    *sum = total;
    *avg = size > 0 ? (double)total / size : 0.0;
}

int main(void) {
    int arr[] = {10, 20, 30, 40, 50};
    int size = (int)(sizeof(arr) / sizeof(arr[0]));
    int sum = 0;
    double avg = 0.0;

    stats(arr, size, &sum, &avg);
    printf("مجموع = %d، میانگین = %.2f\n", sum, avg);
    return 0;
}
