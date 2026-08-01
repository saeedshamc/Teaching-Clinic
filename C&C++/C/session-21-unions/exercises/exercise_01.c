/*
 * تمرین ۱ — union ساده: int و char
 * TODO: اجرا کنید و مشاهده کنید که خواندن عضوی که نوشته نشده، مقدار بیمعنی می‌دهد.
 * در یک کامنت توضیح دهید چرا.
 */

#include <stdio.h>

union Mix {
    int i;
    char c;
};

int main(void) {
    union Mix m;

    m.i = 65;   /* 65 در ASCII حرف A است */
    printf("m.i = %d\n", m.i);
    printf("m.c = %c (چون 65 همان کد A است)\n", m.c);

    m.c = 'Z';
    printf("بعد از m.c = 'Z'، مقدار m.i = %d (خراب شد چون فضا مشترک است)\n", m.i);

    return 0;
}
