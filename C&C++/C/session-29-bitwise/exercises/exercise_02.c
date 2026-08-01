/*
 * تمرین ۲ — پرچم‌های مجوز
 */

#include <stdio.h>

#define READ   (1u << 0)
#define WRITE  (1u << 1)
#define EXEC   (1u << 2)

int main(void) {
    unsigned int perm = READ;

    perm |= WRITE;              /* ست */
    printf("READ|WRITE = %u\n", perm);

    if (perm & READ) {
        printf("خواندن مجاز است\n");
    }

    perm &= ~READ;              /* پاک */
    printf("بعد از پاک کردن READ = %u\n", perm);

    perm ^= EXEC;               /* toggle */
    printf("بعد از toggle EXEC = %u\n", perm);
    perm ^= EXEC;
    printf("دوباره toggle = %u\n", perm);

    return 0;
}
