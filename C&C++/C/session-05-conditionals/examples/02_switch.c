/*
 * جلسه ۵ — switch برای منوی ساده
 */

#include <stdio.h>

int main(void) {
    int choice;

    printf("منو:\n");
    printf("1) چای\n2) قهوه\n3) آب\n");
    printf("انتخاب: ");
    if (scanf("%d", &choice) != 1) {
        printf("ورودی نامعتبر!\n");
        return 1;
    }

    switch (choice) {
        case 1:
            printf("شما چای انتخاب کردید.\n");
            break;
        case 2:
            printf("شما قهوه انتخاب کردید.\n");
            break;
        case 3:
            printf("شما آب انتخاب کردید.\n");
            break;
        default:
            printf("گزینهٔ نامعتبر!\n");
            break;
    }

    return 0;
}
