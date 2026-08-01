/*
 * جلسه ۳۰ — چاپ آرگومان‌های خط فرمان
 * اجرا: ./program salam donya
 */

#include <stdio.h>

int main(int argc, char *argv[]) {
    printf("تعداد آرگومان‌ها (argc) = %d\n", argc);

    for (int i = 0; i < argc; i++) {
        printf("argv[%d] = %s\n", i, argv[i]);
    }

    return 0;
}
