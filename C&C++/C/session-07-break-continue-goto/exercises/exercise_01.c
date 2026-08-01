/*
 * تمرین ۱ — فیلتر و توقف
 * TODO: با حلقهٔ for اعداد ۱ تا ۲۰ را چاپ کنید،
 * اعداد بخش‌پذیر بر ۳ را با continue رد کنید و بعد از عدد ۱۵ متوقف شوید (break).
 * خروجی مورد انتظار: 1 2 4 5 7 8 10 11 13 14
 */

#include <stdio.h>

int main(void) {
    for (int i = 1; i <= 20; i++) {
        if (i % 3 == 0) {
            continue;
        }
        if (i > 15) {
            break;
        }
        printf("%d ", i);
    }
    printf("\n");
    return 0;
}
