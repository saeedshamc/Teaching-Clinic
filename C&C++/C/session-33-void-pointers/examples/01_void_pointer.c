/*
 * جلسه ۳۳ — void *: اشاره‌گر بی‌نوع
 */

#include <stdio.h>

int main(void) {
    int x = 10;
    double d = 3.14;

    void *ptr = &x;        /* آدرس int در void* */
    int *ip = (int *)ptr;  /* بازگرداندن به نوع درست */
    printf("مقدار int: %d\n", *ip);

    ptr = &d;              /* حالا آدرس double */
    double *dp = (double *)ptr;
    printf("مقدار double: %.2f\n", *dp);

    /* توجه: *ptr مستقیم مجاز نیست! */
    /* printf("%d\n", *ptr); ← خطای کامپایل */

    return 0;
}
