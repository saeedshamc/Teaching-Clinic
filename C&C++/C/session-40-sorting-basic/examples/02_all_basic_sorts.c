/*
 * جلسه ۴۰ — هر سه الگوریتم مرتب‌سازی ساده
 */

#include <stdio.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

/* حبابی */
void bubble_sort(int arr[], int n) {
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                swap(&arr[j], &arr[j + 1]);
            }
        }
    }
}

/* انتخابی */
void selection_sort(int arr[], int n) {
    for (int i = 0; i < n - 1; i++) {
        int min_idx = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[min_idx]) {
                min_idx = j;
            }
        }
        swap(&arr[i], &arr[min_idx]);
    }
}

/* درجی */
void insertion_sort(int arr[], int n) {
    for (int i = 1; i < n; i++) {
        int key = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}

void print_array(const int arr[], int n) {
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main(void) {
    int a[] = {64, 34, 25, 12, 22, 11, 90};
    int b[] = {64, 34, 25, 12, 22, 11, 90};
    int c[] = {64, 34, 25, 12, 22, 11, 90};
    int n = (int)(sizeof(a) / sizeof(a[0]));

    bubble_sort(a, n);
    printf("حبابی: ");
    print_array(a, n);

    selection_sort(b, n);
    printf("انتخابی: ");
    print_array(b, n);

    insertion_sort(c, n);
    printf("درجی: ");
    print_array(c, n);

    return 0;
}
