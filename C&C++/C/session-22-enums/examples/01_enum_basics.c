/*
 * جلسه ۲۲ — enum: ثابت‌های نام‌دار
 */

#include <stdio.h>

/* پیش‌فرض: SAT=0, SUN=1, ... */
enum Day { SAT, SUN, MON, TUE, WED, THU, FRI };

/* مقادیر سفارشی */
enum Status { OK = 0, ERROR = -1, WARNING = 1 };

int main(void) {
    enum Day today = WED;
    printf("مقدار WED = %d\n", today);

    /* چاپ همهٔ روزها */
    for (enum Day d = SAT; d <= FRI; d++) {
        printf("%d ", d);
    }
    printf("\n");

    printf("OK = %d، ERROR = %d، WARNING = %d\n", OK, ERROR, WARNING);

    /* استفادهٔ معنادار */
    enum Status result = OK;
    if (result == OK) {
        printf("عملیات موفق بود.\n");
    }

    return 0;
}
