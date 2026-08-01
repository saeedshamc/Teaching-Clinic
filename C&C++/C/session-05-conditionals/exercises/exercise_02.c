/*
 * تمرین ۲ — درجه‌بندی نمره با if-else
 */

#include <stdio.h>

int main(void) {
    int score;

    printf("نمره (۰ تا ۲۰): ");
    if (scanf("%d", &score) != 1) {
        printf("ورودی نامعتبر!\n");
        return 1;
    }

    if (score < 0 || score > 20) {
        printf("نمره خارج از محدوده!\n");
        return 1;
    }

    if (score >= 18) {
        printf("A\n");
    } else if (score >= 15) {
        printf("B\n");
    } else if (score >= 12) {
        printf("C\n");
    } else if (score >= 10) {
        printf("D\n");
    } else {
        printf("E\n");
    }

    return 0;
}
