/*
 * جلسه ۴۱ — مرتب‌سازی سریع (Quick Sort)
 */

#include <stdio.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

/* پارتیشن‌بندی به سبک Lomuto؛ محور = عنصر آخر */
int partition(int arr[], int low, int high) {
    int pivot = arr[high];
    int i = low - 1;   /* مرز عناصر کوچک‌تر از محور */

    for (int j = low; j < high; j++) {
        if (arr[j] <= pivot) {
            i++;
            swap(&arr[i], &arr[j]);
        }
    }

    /* محور را در جای نهایی‌اش بگذار */
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
    int numbers[] = {10, 7, 8, 9, 1, 5};
    int n = (int)(sizeof(numbers) / sizeof(numbers[0]));

    printf("قبل: ");
    print_array(numbers, n);

    quick_sort(numbers, 0, n - 1);

    printf("بعد: ");
    print_array(numbers, n);

    return 0;
}
