/*
 * تمرین ۲ — تابع apply با callback
 */

#include <stdio.h>

int double_it(int x) { return x * 2; }
int square_it(int x) { return x * x; }

typedef int (*Transform)(int);

void apply(int *arr, int size, Transform f) {
    for (int i = 0; i < size; i++) {
        arr[i] = f(arr[i]);
    }
}

void print_array(const int *arr, int size) {
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main(void) {
    int a[] = {1, 2, 3, 4, 5};
    int size = (int)(sizeof(a) / sizeof(a[0]));

    apply(a, size, double_it);
    printf("دو برابر: ");
    print_array(a, size);

    apply(a, size, square_it);
    printf("مربع: ");
    print_array(a, size);

    return 0;
}
