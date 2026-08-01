/*
 * تمرین ۲ — نام و سن با مدیریت بافر
 * راهنمایی: بعد از scanf برای عدد، \n در بافر می‌ماند؛
 * با getchar آن را بخوانید و بعد fgets را برای نام استفاده کنید.
 */

#include <stdio.h>

int main(void) {
    char name[50];
    int age;

    printf("سن: ");
    if (scanf("%d", &age) != 1) {
        printf("ورودی نامعتبر!\n");
        return 1;
    }

    /* پاک کردن \n باقی‌مانده در بافر — با بررسی EOF تا بی‌نهایت نشود */
    int ch;
    while ((ch = getchar()) != '\n' && ch != EOF) {
        /* تا پایان خط بخوان */
    }

    printf("نام: ");
    if (fgets(name, sizeof(name), stdin) == NULL) {
        printf("خطای ورودی!\n");
        return 1;
    }

    /* حذف \n انتهای رشته */
    for (int i = 0; name[i] != '\0'; i++) {
        if (name[i] == '\n') {
            name[i] = '\0';
            break;
        }
    }

    printf("سلام %s، سن شما %d است.\n", name, age);
    return 0;
}
