/*
 * جلسه ۱۹ — struct: تعریف، مقداردهی و دسترسی
 */

#include <stdio.h>
#include <string.h>

struct Student {
    char name[50];
    int age;
    double gpa;
};

int main(void) {
    /* ساخت و مقداردهی عضو به عضو */
    struct Student s1;
    strcpy(s1.name, "Ali");
    s1.age = 20;
    s1.gpa = 17.5;

    /* مقداردهی اولیه */
    struct Student s2 = {"Sara", 22, 18.25};

    /* چاپ */
    printf("دانشجو ۱: %s، %d ساله، معدل %.2f\n", s1.name, s1.age, s1.gpa);
    printf("دانشجو ۲: %s، %d ساله، معدل %.2f\n", s2.name, s2.age, s2.gpa);

    /* کپی کل struct با = */
    struct Student s3 = s1;
    printf("کپی s1: %s\n", s3.name);

    /* تغییر عضو */
    s3.gpa = 19.0;
    printf("معدل s3 جدید: %.2f (s1 دست‌نخورده: %.2f)\n", s3.gpa, s1.gpa);

    return 0;
}
