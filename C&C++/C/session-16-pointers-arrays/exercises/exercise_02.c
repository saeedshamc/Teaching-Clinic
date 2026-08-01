/*
 * تمرین ۲ — تابع مجموع با اشاره‌گر
 */

#include <stdio.h>

int sum_array(int *arr, int size) {
    int sum = 0;
    for (int *p = arr; p < arr + size; p++) {
        sum += *p;
    }
    return sum;
}

int main(void) {
    int numbers[] = {5, 10, 15, 20, 25};
    int size = (int)(sizeof(numbers) / sizeof(numbers[0]));

    printf("مجموع = %d\n", sum_array(numbers, size));   /* 75 */
    return 0;
}
