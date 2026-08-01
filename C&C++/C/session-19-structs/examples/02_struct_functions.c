/*
 * جلسه ۱۹ — struct در تابع (با مقدار و با اشاره‌گر)
 */

#include <stdio.h>
#include <string.h>

struct Student {
    char name[50];
    int age;
    double gpa;
};

/* با مقدار: فقط کپی — تغییرات بیرون نمی‌ماند */
void print_student(struct Student s) {
    printf("%s، %d ساله، معدل %.2f\n", s.name, s.age, s.gpa);
}

/* با اشاره‌گر: می‌توان تغییر داد (و کپی نمی‌شود) */
void increase_gpa(struct Student *s, double amount) {
    s->gpa += amount;   /* s->gpa معادل (*s).gpa است */
}

int main(void) {
    struct Student s = {"Mina", 21, 16.0};

    print_student(s);
    increase_gpa(&s, 1.5);
    printf("بعد از افزایش معدل:\n");
    print_student(s);

    return 0;
}
