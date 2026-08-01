/*
 * جلسه ۱۳ — خواندن امن رشته با fgets
 */

#include <stdio.h>
#include <string.h>   /* برای strlen */

int main(void) {
    char name[50];

    printf("نام خود را وارد کنید: ");
    if (fgets(name, sizeof(name), stdin) == NULL) {
        printf("خطای ورودی!\n");
        return 1;
    }

    /* fgets \n انتهای خط را هم نگه می‌دارد؛ حذفش می‌کنیم */
    size_t len = strlen(name);
    if (len > 0 && name[len - 1] == '\n') {
        name[len - 1] = '\0';
    }

    printf("سلام %s!\n", name);
    return 0;
}
