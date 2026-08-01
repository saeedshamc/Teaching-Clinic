/*
 * جلسه ۲۳ — typedef برای struct
 */

#include <stdio.h>
#include <string.h>

/* بدون typedef مجبوریم هر بار struct Student بنویسیم */
typedef struct {
    char name[50];
    int age;
} Student;

int main(void) {
    Student s1;               /* دیگر struct لازم نیست */
    strcpy(s1.name, "Ali");
    s1.age = 20;

    Student students[3] = {
        {"Ali", 20},
        {"Sara", 22},
        {"Mina", 21}
    };

    printf("دانشجو: %s، %d ساله\n", s1.name, s1.age);

    for (int i = 0; i < 3; i++) {
        printf("%d) %s\n", i + 1, students[i].name);
    }

    return 0;
}
