/*
 * تمرین ۱ — تابع print_any با void *
 * TODO: تابعی بنویسید که با flag مشخص کند مقدار int است یا double و درست چاپ کند.
 */

#include <stdio.h>

enum DataType { TYPE_INT, TYPE_DOUBLE };

void print_any(const void *data, enum DataType type) {
    if (type == TYPE_INT) {
        const int *ip = (const int *)data;
        printf("int: %d\n", *ip);
    } else {
        const double *dp = (const double *)data;
        printf("double: %.2f\n", *dp);
    }
}

int main(void) {
    int x = 42;
    double d = 3.14159;

    print_any(&x, TYPE_INT);
    print_any(&d, TYPE_DOUBLE);
    return 0;
}
