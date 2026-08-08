/*
 * جلسه ۴۲ — جستجوی دودویی (Binary Search) — تکراری و بازگشتی
 */

#include <stdio.h>

/* نسخهٔ تکراری */
int binary_search(const int arr[], int n, int target) {
    int low = 0, high = n - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;   /* وسطِ امن */

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            low = mid + 1;      /* به نیمهٔ راست برو */
        } else {
            high = mid - 1;     /* به نیمهٔ چپ برو */
        }
    }
    return -1;
}

/* نسخهٔ بازگشتی */
int binary_search_recursive(const int arr[], int low, int high, int target) {
    if (low > high) {
        return -1;   /* بازه خالی شد؛ پیدا نشد */
    }

    int mid = low + (high - low) / 2;
    if (arr[mid] == target) {
        return mid;
    }
    if (arr[mid] < target) {
        return binary_search_recursive(arr, mid + 1, high, target);
    }
    return binary_search_recursive(arr, low, mid - 1, target);
}

int main(void) {
    /* آرایه باید مرتب باشد! */
    int numbers[] = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
    int n = (int)(sizeof(numbers) / sizeof(numbers[0]));

    int targets[] = {23, 1, 91};

    for (int i = 0; i < 3; i++) {
        int idx = binary_search(numbers, n, targets[i]);
        int idx2 = binary_search_recursive(numbers, 0, n - 1, targets[i]);

        if (idx != -1) {
            printf("%d در ایندکس %d پیدا شد (تکراری: %d، بازگشتی: %d)\n",
                   targets[i], idx, idx, idx2);
        } else {
            printf("%d پیدا نشد\n", targets[i]);
        }
    }

    return 0;
}
