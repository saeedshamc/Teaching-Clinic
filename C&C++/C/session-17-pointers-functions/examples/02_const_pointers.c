/*
 * جلسه ۱۷ — const در پارامتر و خروجی‌های چندگانه
 */

#include <stdio.h>

/* فقط خواندن آرایه — const یعنی تغییرش نمی‌دهیم */
void print_array(const int *arr, int size) {
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

/* دو خروجی از یک تابع: مجموع و حاصل‌ضرب */
void sum_and_product(int a, int b, int *sum, int *product) {
    *sum = a + b;
    *product = a * b;
}

int main(void) {
    int arr[] = {1, 2, 3, 4};
    print_array(arr, 4);

    int s = 0, p = 0;
    sum_and_product(5, 7, &s, &p);
    printf("مجموع = %d، حاصل‌ضرب = %d\n", s, p);

    return 0;
}
