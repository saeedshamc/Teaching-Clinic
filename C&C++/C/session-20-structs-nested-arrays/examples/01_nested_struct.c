/*
 * جلسه ۲۰ — struct تودرتو
 */

#include <stdio.h>
#include <string.h>

struct Date {
    int day;
    int month;
    int year;
};

struct Student {
    char name[50];
    struct Date birth;   /* struct داخل struct */
};

int main(void) {
    struct Student s;
    strcpy(s.name, "Ali");
    s.birth.day = 15;
    s.birth.month = 3;
    s.birth.year = 2005;

    printf("نام: %s\n", s.name);
    printf("تولد: %d/%d/%d\n", s.birth.year, s.birth.month, s.birth.day);

    return 0;
}
