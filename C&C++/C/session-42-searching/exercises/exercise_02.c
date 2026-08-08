/*
 * تمرین ۲ — پیدا کردن اولین رخداد عدد تکراری با جستجوی دودویی
 * آرایه: {1, 2, 2, 2, 3, 4, 5} → اولین رخداد 2 باید ایندکس 1 باشد
 */

#include <stdio.h>

/* اولین رخداد را برمی‌گرداند؛ حتی بعد از پیدا شدن، به چپ ادامه می‌دهیم */
int binary_search_first(const int arr[], int n, int target) {
    int low = 0, high = n - 1;
    int result = -1;

    while (low <= high) {
        int mid = low + (high - low) / 2;

        if (arr[mid] == target) {
            result = mid;      /* یک رخداد پیدا شد */
            high = mid - 1;    /* ولی شاید رخداد اولی در چپ باشد */
        } else if (arr[mid] < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return result;
}

int main(void) {
    int numbers[] = {1, 2, 2, 2, 3, 4, 5, 5, 6};
    int n = (int)(sizeof(numbers) / sizeof(numbers[0]));

    int idx = binary_search_first(numbers, n, 2);
    printf("اولین رخداد 2 در ایندکس %d\n", idx);

    int idx5 = binary_search_first(numbers, n, 5);
    printf("اولین رخداد 5 در ایندکس %d\n", idx5);

    return 0;
}
