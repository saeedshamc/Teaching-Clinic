/*
 * تمرین ۱ — رفع نشت برنامهٔ نمونه
 *
 * برنامهٔ زیر نشت دارد: در تابع create_buffer حافظه تخصیص داده
 * می‌شود ولی free نمی‌شود. با valgrind نشت را ببینید و سپس با
 * اضافه کردن free (و NULL کردن اشاره‌گر) آن را رفع کنید.
 */

#include <stdio.h>
#include <stdlib.h>

int *create_buffer(int n) {
    int *buf = (int *)malloc((size_t)n * sizeof(int));
    if (buf == NULL) {
        fprintf(stderr, "خطای تخصیص حافظه\n");
        return NULL;
    }

    for (int i = 0; i < n; i++) {
        buf[i] = i + 1;
    }
    return buf;
}

int main(void) {
    int n = 8;
    int *buf = create_buffer(n);

    if (buf == NULL) {
        return 1;
    }

    printf("بافر:\n");
    for (int i = 0; i < n; i++) {
        printf("  %d ", buf[i]);
    }
    printf("\n");

    /* راه‌حل: اضافه کردن آزادسازی */
    free(buf);
    buf = NULL;

    return 0;
}
