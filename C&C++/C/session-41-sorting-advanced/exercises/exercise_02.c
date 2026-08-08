/*
 * تمرین ۲ — مرتب‌سازی آرایه‌ای از struct با qsort استاندارد
 * بر اساس نمره به صورت نزولی
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char name[50];
    int score;
} Student;

/* مقایسه‌گر نزولی بر اساس نمره */
int compare_by_score_desc(const void *a, const void *b) {
    const Student *sa = (const Student *)a;
    const Student *sb = (const Student *)b;
    return sb->score - sa->score;
}

int main(void) {
    Student students[] = {
        {"ali", 18},
        {"sara", 20},
        {"reza", 15},
        {"mina", 19}
    };
    int n = (int)(sizeof(students) / sizeof(students[0]));

    qsort(students, (size_t)n, sizeof(Student), compare_by_score_desc);

    printf("رتبه‌بندی بر اساس نمره (نزولی):\n");
    for (int i = 0; i < n; i++) {
        printf("%d. %s — %d\n", i + 1, students[i].name, students[i].score);
    }

    return 0;
}
