/*
 * جلسه ۱۳ — رشته به‌عنوان آرایهٔ char با پایانهٔ \0
 */

#include <stdio.h>

int main(void) {
    /* دو روش تعریف */
    char greeting[] = "Salam";
    char name[20] = "Ali";

    printf("سلام به %s\n", name);
    printf("greeting = %s\n", greeting);

    /* پیمایش دستی تا \0 */
    printf("حروف greeting: ");
    for (int i = 0; greeting[i] != '\0'; i++) {
        printf("%c ", greeting[i]);
    }
    printf("\n");

    /* اندازه‌ی آرایه: +1 برای \0 */
    printf("اندازهٔ آرایهٔ greeting = %zu بایت\n", sizeof(greeting));
    printf("تعداد حروف (تا کاراکتر پایانی \\0) = 5\n");

    /* ساخت دستی رشته */
    char word[4];
    word[0] = 'C';
    word[1] = 'A';
    word[2] = 'T';
    word[3] = '\0';   /* بدون این، چاپ خراب می‌شود */
    printf("word = %s\n", word);

    return 0;
}
