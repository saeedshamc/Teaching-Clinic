/*
 * جلسه ۱۶ — اشاره‌گر حسابی و تغییر عناصر
 */

#include <stdio.h>

int main(void) {
    int arr[5] = {1, 2, 3, 4, 5};
    int *p = arr;

    /* جلو/عقب رفتن */
    printf("اولین: %d\n", *p);
    printf("سومی (p+2): %d\n", *(p + 2));
    p = p + 4;
    printf("آخرین (p+4): %d\n", *p);

    /* اشاره‌گر به وسط */
    int *mid = arr + 2;
    printf("وسط: %d\n", *mid);

    /* تفریق دو اشاره‌گر: فاصله بر حسب عنصر (نوع ptrdiff_t با %td) */
    printf("فاصلهٔ mid و اول: %td\n", mid - arr);

    /* تغییر از طریق اشاره‌گر */
    *mid = 99;
    printf("بعد از تغییر: arr[2] = %d\n", arr[2]);

    return 0;
}
