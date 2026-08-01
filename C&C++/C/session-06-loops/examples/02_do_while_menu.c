/*
 * جلسه ۶ — منوی ساده با do-while
 */

#include <stdio.h>

int main(void) {
    int choice;

    /* بدنه حداقل یک بار اجرا می‌شود */
    do {
        printf("منو:\n");
        printf("1) چای\n2) قهوه\n3) خروج\n");
        printf("انتخاب: ");
        if (scanf("%d", &choice) != 1) {
            /* ورودی نامعتبر: بافر را پاک کن (با بررسی EOF) */
            int ch;
            while ((ch = getchar()) != '\n' && ch != EOF) {
                /* بخوان و دور بریز */
            }
            choice = 0;   /* تا حلقه ادامه یابد */
            continue;
        }
        if (choice == 1) {
            printf("چای آماده شد!\n");
        } else if (choice == 2) {
            printf("قهوه آماده شد!\n");
        }
    } while (choice != 3);

    printf("خداحافظ!\n");
    return 0;
}
