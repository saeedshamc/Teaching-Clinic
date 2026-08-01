/*
 * تمرین ۲ — بزرگ‌ترین سه عدد از خط فرمان
 * اجرا: ./program 12 45 9  → 45
 */

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
    if (argc != 4) {
        printf("استفاده: %s <عدد۱> <عدد۲> <عدد۳>\n", argv[0]);
        return 1;
    }

    int a = atoi(argv[1]);
    int b = atoi(argv[2]);
    int c = atoi(argv[3]);

    int max = a;
    if (b > max) {
        max = b;
    }
    if (c > max) {
        max = c;
    }

    printf("بزرگ‌ترین: %d\n", max);
    return 0;
}
