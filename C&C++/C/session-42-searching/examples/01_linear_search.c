/*
 * جلسه ۴۲ — جستجوی خطی (Linear Search)
 */

#include <stdio.h>

/* جستجوی خطی: ایندکس هدف یا -1 */
int linear_search(const int arr[], int n, int target) {
    for (int i = 0; i < n; i++) {
        if (arr[i] == target) {
            return i;
        }
    }
    return -1;
}

int main(void) {
    int numbers[] = {5, 12, 3, 78, 34, 9, 21};
    int n = (int)(sizeof(numbers) / sizeof(numbers[0]));

    int targets[] = {78, 100};

    for (int i = 0; i < 2; i++) {
        int idx = linear_search(numbers, n, targets[i]);
        if (idx != -1) {
            printf("%d در ایندکس %d پیدا شد\n", targets[i], idx);
        } else {
            printf("%d پیدا نشد\n", targets[i]);
        }
    }

    return 0;
}
