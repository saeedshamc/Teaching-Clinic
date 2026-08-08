/*
 * جلسه ۴۱ — مرتب‌سازی ادغامی (Merge Sort)
 */

#include <stdio.h>

/* ادغام دو نیمهٔ مرتب [l..m] و [m+1..r] */
void merge(int arr[], int l, int m, int r) {
    int n1 = m - l + 1;
    int n2 = r - m;

    /* آرایه‌های موقت برای دو نیمه */
    int L[n1], R[n2];

    for (int i = 0; i < n1; i++) {
        L[i] = arr[l + i];
    }
    for (int j = 0; j < n2; j++) {
        R[j] = arr[m + 1 + j];
    }

    /* ادغام: همیشه کوچک‌ترین عنصر فعلی را برمی‌داریم */
    int i = 0, j = 0, k = l;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    /* باقی‌مانده‌های نیمه‌ها (اگر چیزی اضافه ماند) */
    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}

void merge_sort(int arr[], int l, int r) {
    if (l < r) {
        int m = l + (r - l) / 2;   /* وسطِ امن بدون سرریز */
        merge_sort(arr, l, m);
        merge_sort(arr, m + 1, r);
        merge(arr, l, m, r);
    }
}

void print_array(const int arr[], int n) {
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main(void) {
    int numbers[] = {38, 27, 43, 3, 9, 82, 10};
    int n = (int)(sizeof(numbers) / sizeof(numbers[0]));

    printf("قبل: ");
    print_array(numbers, n);

    merge_sort(numbers, 0, n - 1);

    printf("بعد: ");
    print_array(numbers, n);

    return 0;
}
