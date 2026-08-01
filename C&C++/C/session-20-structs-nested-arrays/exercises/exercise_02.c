/*
 * تمرین ۲ — میانگین معدل دانشجویان
 */

#include <stdio.h>

struct Student {
    char name[50];
    double gpa;
};

int main(void) {
    struct Student students[3] = {
        {"Ali", 17.5},
        {"Sara", 18.25},
        {"Mina", 16.75}
    };

    double total = 0.0;
    for (int i = 0; i < 3; i++) {
        total += students[i].gpa;
        printf("%s: معدل %.2f\n", students[i].name, students[i].gpa);
    }

    printf("میانگین معدل‌ها: %.2f\n", total / 3);
    return 0;
}
