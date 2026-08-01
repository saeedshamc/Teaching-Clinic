/*
 * تمرین ۱ — مقایسهٔ دو رشته با strcmp
 */

#include <stdio.h>
#include <string.h>

int main(void) {
    char a[50], b[50];

    printf("رشتهٔ اول: ");
    if (fgets(a, sizeof(a), stdin) == NULL) {
        return 1;
    }
    printf("رشتهٔ دوم: ");
    if (fgets(b, sizeof(b), stdin) == NULL) {
        return 1;
    }

    /* حذف \n انتهای هر رشته */
    a[strcspn(a, "\n")] = '\0';
    b[strcspn(b, "\n")] = '\0';

    int cmp = strcmp(a, b);
    if (cmp == 0) {
        printf("دو رشته برابرند.\n");
    } else if (cmp < 0) {
        printf("رشتهٔ اول کوچک‌تر است.\n");
    } else {
        printf("رشتهٔ اول بزرگ‌تر است.\n");
    }
    return 0;
}
