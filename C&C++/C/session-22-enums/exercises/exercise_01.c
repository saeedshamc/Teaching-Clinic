/*
 * تمرین ۱ — ماه‌های فصل بهار
 * TODO: enum برای FARVARDIN، ORDIBEHESHT و KHORDAD با مقادیر ۱ تا ۳ بسازید
 * و مقدار KHORDAD را چاپ کنید.
 */

#include <stdio.h>

enum SpringMonth {
    FARVARDIN = 1,
    ORDIBEHESHT = 2,
    KHORDAD = 3
};

int main(void) {
    enum SpringMonth month = KHORDAD;
    printf("مقدار KHORDAD = %d\n", month);
    printf("مقدار FARVARDIN = %d\n", FARVARDIN);
    return 0;
}
