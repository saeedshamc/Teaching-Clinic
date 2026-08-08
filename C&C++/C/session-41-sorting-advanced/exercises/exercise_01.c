/*
 * تمرین ۱ — پیاده‌سازی Quick Sort روی آرایهٔ ۱۰ عنصری
 */

#include <stdio.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

int partition(int arr[], int low, int high) {
    int pivot = arr[high];
    int i = low - 1;

    for (int j = low; j < high; j++) {
        if (arr[j] <= pivot) {
            i++;
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]);
    return i + 1;
}

void quick_sort(int arr[], int low, int high) {
    if (low < high) {
        int p = partition(arr, low, high);
        quick_sort(arr, low, p - 1);
        quick_sort(arr, p + 1, high);
    }
}

void print_array(const int arr[], int n) {
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main(void) {
    int numbers[] = {9, 2, 7, 1, 8, 3, 6, 4, 5, 0};
    int n = (int)(sizeof(numbers) / sizeof(numbers[0]));

    printf("قبل: ");
    print_array(numbers, n);

    quick_sort(numbers, 0, n - 1);

    printf("بعد: ");
    print_array(numbers, n);

    return 0;
}
