/*
 * تمرین ۱ — struct کتاب
 * TODO: struct Book با اعضای title، author و year بسازید،
 * یک نمونه مقداردهی کنید و چاپ کنید.
 */

#include <stdio.h>
#include <string.h>

struct Book {
    char title[100];
    char author[100];
    int year;
};

int main(void) {
    struct Book b;

    strcpy(b.title, "زبان و اندیشه");
    strcpy(b.author, "احمد شاملو");
    b.year = 1960;

    printf("کتاب: %s\n", b.title);
    printf("نویسنده: %s\n", b.author);
    printf("سال: %d\n", b.year);

    return 0;
}
