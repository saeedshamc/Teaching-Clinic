/*
 * جلسه ۲۴ — خواندن خط‌به‌خط تا پایان فایل با fgets
 */

#include <stdio.h>

int main(void) {
    /* یک فایل آزمایشی می‌سازیم */
    FILE *fp = fopen("lines.txt", "w");
    if (fp == NULL) {
        return 1;
    }
    fputs("سطر ۱\n", fp);
    fputs("سطر ۲\n", fp);
    fputs("سطر ۳\n", fp);
    fclose(fp);

    /* خواندن و شمارش سطرها */
    fp = fopen("lines.txt", "r");
    if (fp == NULL) {
        return 1;
    }

    char line[128];
    int count = 0;
    while (fgets(line, sizeof(line), fp) != NULL) {
        count++;
        printf("سطر %d: %s", count, line);
    }

    printf("تعداد کل سطرها: %d\n", count);
    fclose(fp);
    return 0;
}
