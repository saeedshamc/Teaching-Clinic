/*
 * جلسه ۳ — عملگرهای حسابی، افزایش/کاهش و ترکیبی
 */

#include <stdio.h>

int main(void) {
    int a = 10, b = 3;

    printf("a + b = %d\n", a + b);
    printf("a - b = %d\n", a - b);
    printf("a * b = %d\n", a * b);
    printf("a / b = %d\n", a / b);    /* تقسیم صحیح → 3 */
    printf("a %% b = %d\n", a % b);   /* باقیمانده → 1 */

    /* تقسیم اعشاری با حداقل یک عملوند اعشاری */
    printf("a / 3.0 = %.2f\n", a / 3.0);

    /* افزایش پیشوند و پسوند */
    int x = 5;
    printf("x = %d\n", x);
    /* پسوند: اول مقدار فعلی چاپ می‌شود (5)، بعد x می‌شود 6 */
    printf("x++ = %d\n", x++);
    printf("بعد از x++ مقدار x = %d\n", x);
    /* پیشوند: اول x زیاد می‌شود (7)، بعد چاپ می‌شود */
    printf("++x = %d\n", ++x);

    /* عملگر ترکیبی */
    int total = 100;
    total += 25;     /* معادل total = total + 25 */
    printf("total = %d\n", total);
    total /= 5;
    printf("total = %d\n", total);

    return 0;
}
