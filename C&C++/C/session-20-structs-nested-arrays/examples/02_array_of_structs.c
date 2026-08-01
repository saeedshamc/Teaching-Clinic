/*
 * جلسه ۲۰ — آرایه‌ای از struct و عملگر ->
 */

#include <stdio.h>

struct Student {
    char name[50];
    int age;
    double gpa;
};

int main(void) {
    struct Student students[3] = {
        {"Ali", 20, 17.5},
        {"Sara", 22, 18.25},
        {"Mina", 21, 16.75}
    };

    /* پیمایش آرایهٔ struct */
    printf("لیست دانشجویان:\n");
    for (int i = 0; i < 3; i++) {
        printf("%d) %s — %d ساله — معدل %.2f\n",
               i + 1, students[i].name, students[i].age, students[i].gpa);
    }

    /* استفاده از اشاره‌گر به struct و عملگر -> */
    struct Student *p = &students[1];
    p->age = 23;   /* معادل (*p).age = 23 */
    printf("سن سارا بعد از تغییر: %d\n", p->age);

    return 0;
}
