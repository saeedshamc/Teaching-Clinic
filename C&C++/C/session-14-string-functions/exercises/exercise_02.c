/*
 * تمرین ۲ — الحاق دو رشته با strcpy و strcat
 */

#include <stdio.h>
#include <string.h>

int main(void) {
    char first[50], second[50];
    char combined[100];

    printf("نام: ");
    if (fgets(first, sizeof(first), stdin) == NULL) {
        return 1;
    }
    printf("نام خانوادگی: ");
    if (fgets(second, sizeof(second), stdin) == NULL) {
        return 1;
    }

    first[strcspn(first, "\n")] = '\0';
    second[strcspn(second, "\n")] = '\0';

    /* کپی و الحاق — بافر ۱۰۰ خونه برای دو تا ۵۰ خونه ای کافی است */
    strcpy(combined, first);
    strcat(combined, " ");
    strcat(combined, second);

    printf("نام کامل: %s\n", combined);
    return 0;
}
