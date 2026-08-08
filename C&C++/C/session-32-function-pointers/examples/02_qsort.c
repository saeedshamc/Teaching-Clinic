/*
 * جلسه ۳۲ — qsort با اشاره‌گر به تابع مقایسه‌گر
 */

#include <stdio.h>
#include <stdlib.h>

/* مقایسهٔ دو int برای qsort — امضای دقیق الزامی است */
int compare_int(const void *a, const void *b) {
    int x = *(const int *)a;
    int y = *(const int *)b;
    return (x > y) - (x < y);   /* -1, 0, یا 1 */
}

int compare_int_desc(const void *a, const void *b) {
    return compare_int(b, a);   /* برعکس = نزولی */
}

void print_array(const int *arr, int size) {
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main(void) {
    int numbers[] = {42, 7, 19, 3, 88, 1};
    int size = (int)(sizeof(numbers) / sizeof(numbers[0]));

    qsort(numbers, (size_t)size, sizeof(int), compare_int);
    printf("صعودی: ");
    print_array(numbers, size);

    qsort(numbers, (size_t)size, sizeof(int), compare_int_desc);
    printf("نزولی: ");
    print_array(numbers, size);

    return 0;
}
