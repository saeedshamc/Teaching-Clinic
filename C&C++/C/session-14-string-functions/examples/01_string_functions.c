/*
 * جلسه ۱۴ — توابع اصلی string.h
 */

#include <stdio.h>
#include <string.h>

int main(void) {
    char src[] = "C Programming";
    char dest[64] = "";

    /* strlen: طول بدون \0 */
    printf("طول '%s' = %zu\n", src, strlen(src));

    /* strcpy: کپی */
    strcpy(dest, src);
    printf("کپی شده: %s\n", dest);

    /* strcmp: مقایسه */
    if (strcmp(dest, src) == 0) {
        printf("dest و src برابرند\n");
    }

    char other[] = "C Programming!";
    printf("strcmp(dest, other) = %d\n", strcmp(dest, other));

    /* strchr: پیدا کردن حرف */
    char *found = strchr(src, 'P');
    if (found != NULL) {
        /* %td برای ptrdiff_t (تفریق دو اشاره‌گر) */
        printf("حرف P در ایندکس %td پیدا شد\n", found - src);
    }

    /* strstr: پیدا کردن زیررشته */
    char *sub = strstr(src, "ogram");
    if (sub != NULL) {
        printf("زیررشته یافت شد: '%s'\n", sub);
    }

    return 0;
}
