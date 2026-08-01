/*
 * جلسه ۲۹ — الگوی پرچم با عملگرهای بیتی
 */

#include <stdio.h>

#define READ   (1u << 0)   /* 0001 */
#define WRITE  (1u << 1)   /* 0010 */
#define EXEC   (1u << 2)   /* 0100 */

int main(void) {
    unsigned int perm = 0;

    /* ست کردن */
    perm |= READ;
    perm |= WRITE;
    printf("مجوز اولیه: %u (READ|WRITE)\n", perm);

    /* بررسی */
    if (perm & READ) {
        printf("مجوز خواندن دارد.\n");
    }

    /* پاک کردن */
    perm &= ~WRITE;
    printf("بعد از پاک کردن WRITE: %u\n", perm);

    /* تغییر (toggle) */
    perm ^= EXEC;
    printf("بعد از toggle EXEC: %u\n", perm);

    /* چاپ به صورت باینری ساده */
    printf("ارزش نهایی: %u\n", perm);

    return 0;
}
