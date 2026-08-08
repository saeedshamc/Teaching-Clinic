/*
 * جلسه ۴۳ — تابع هش و نمایش برخوردها
 */

#include <stdio.h>
#include <string.h>

#define TABLE_SIZE 7

/* تابع هش: هر کاراکتر را در 31 ضرب و جمع می‌کنیم */
unsigned hash(const char *key) {
    unsigned h = 0;
    for (int i = 0; key[i] != '\0'; i++) {
        h = h * 31 + (unsigned char)key[i];
    }
    return h % TABLE_SIZE;
}

int main(void) {
    const char *names[] = {"ali", "sara", "reza", "mina", "navid", "hani"};
    int count = (int)(sizeof(names) / sizeof(names[0]));

    printf("جدول ایندکس‌ها برای اندازهٔ %d:\n", TABLE_SIZE);
    for (int i = 0; i < count; i++) {
        printf("  %-8s -> %u\n", names[i], hash(names[i]));
    }

    /* قانون طلایی: کلید مساوی همیشه هش مساوی بدهد */
    printf("\nبررسی یکسانی هش:\n");
    printf("  hash(\"ali\") = %u , hash(\"ali\") = %u\n",
           hash("ali"), hash("ali"));

    /* برخورد: اگر دو کلید مختلف هش یکسان گرفتند */
    printf("\nاگر دو نام در یک ایندکس افتادند = برخورد (collision)\n");
    return 0;
}
