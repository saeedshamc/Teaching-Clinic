/*
 * تمرین ۱ — جستجوی دودویی روی آرایهٔ ۱۲ عنصری
 * سه عدد: دو عدد موجود و یک عدد غیرموجود
 */

#include <stdio.h>

int binary_search(const int arr[], int n, int target) {
    int low = 0, high = n - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return -1;
}

int main(void) {
    int numbers[] = {3, 7, 11, 15, 18, 22, 27, 31, 40, 45, 50, 58};
    int n = (int)(sizeof(numbers) / sizeof(numbers[0]));

    int targets[] = {22, 58, 100};

    for (int i = 0; i < 3; i++) {
        int idx = binary_search(numbers, n, targets[i]);
        if (idx != -1) {
            printf("%d در ایندکس %d پیدا شد\n", targets[i], idx);
        } else {
            printf("%d پیدا نشد\n", targets[i]);
        }
    }

    return 0;
}
