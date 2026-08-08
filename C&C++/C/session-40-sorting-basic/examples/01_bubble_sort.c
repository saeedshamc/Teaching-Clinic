/*
 * جلسه ۴۰ — مرتب‌سازی حبابی
 */

#include <stdio.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void bubble_sort(int arr[], int n) {
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                swap(&arr[j], &arr[j + 1]);
            }
        }
    }
}

void print_array(const int arr[], int n) {
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main(void) {
    int numbers[] = {64, 34, 25, 12, 22, 11, 90};
    int n = (int)(sizeof(numbers) / sizeof(numbers[0]));

    printf("قبل: ");
    print_array(numbers, n);

    bubble_sort(numbers, n);

    printf("بعد: ");
    print_array(numbers, n);

    return 0;
}
