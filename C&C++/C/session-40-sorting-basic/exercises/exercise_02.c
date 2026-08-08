/*
 * تمرین ۲ — شمارش مقایسه‌ها و جابه‌جایی‌ها در حبابی
 */

#include <stdio.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void bubble_sort_counted(int arr[], int n, int *comparisons, int *swaps) {
    *comparisons = 0;
    *swaps = 0;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - 1 - i; j++) {
            (*comparisons)++;
            if (arr[j] > arr[j + 1]) {
                swap(&arr[j], &arr[j + 1]);
                (*swaps)++;
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
    int comparisons = 0, swaps = 0;

    bubble_sort_counted(numbers, n, &comparisons, &swaps);

    printf("مرتب: ");
    print_array(numbers, n);
    printf("تعداد مقایسه‌ها: %d\n", comparisons);
    printf("تعداد جابه‌جایی‌ها: %d\n", swaps);

    return 0;
}
