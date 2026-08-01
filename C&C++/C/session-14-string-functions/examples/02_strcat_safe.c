/*
 * جلسه ۱۴ — الحاق امن با strncat و snprintf
 */

#include <stdio.h>
#include <string.h>

int main(void) {
    char full_name[64] = "Ali";
    char family[] = " Mohammadi";

    /* strncat: حداکثر N کاراکتر اضافه می‌کند */
    size_t space = sizeof(full_name) - strlen(full_name) - 1;
    strncat(full_name, family, space);
    printf("نام کامل: %s\n", full_name);

    /* snprintf: روش امن‌تر و انعطاف‌پذیرتر */
    /* بافر را به‌اندازهٔ کافی بزرگ می‌گیریم تا پیام کامل جا شود */
    char message[128];
    snprintf(message, sizeof(message), "خوش آمدید، %s! سن شما: %d", full_name, 25);
    printf("%s\n", message);

    return 0;
}
