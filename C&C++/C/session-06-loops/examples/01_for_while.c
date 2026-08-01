/*
 * جلسه ۶ — حلقه‌های for و while
 */

#include <stdio.h>

int main(void) {
    /* for: تعداد تکرار معلوم */
    printf("اعداد ۰ تا ۴: ");
    for (int i = 0; i < 5; i++) {
        printf("%d ", i);
    }
    printf("\n");

    /* while: مجموع ۱ تا ۱۰ */
    int n = 1, sum = 0;
    while (n <= 10) {
        sum += n;
        n++;
    }
    printf("مجموع ۱ تا ۱۰ = %d\n", sum);

    /* while با شرط اولیهٔ نادرست: بدنه اجرا نمی‌شود */
    int count = 0;
    while (count > 5) {
        printf("این خط چاپ نمی‌شود\n");
    }

    return 0;
}
