/*
 * جلسه ۲۵ — ذخیرهٔ struct در فایل باینری با fwrite/fread
 */

#include <stdio.h>
#include <string.h>

typedef struct {
    char name[50];
    int age;
    double gpa;
} Student;

int main(void) {
    Student s1 = {"Ali", 20, 17.5};
    Student s2;

    /* نوشتن باینری */
    FILE *fp = fopen("student.bin", "wb");
    if (fp == NULL) {
        printf("خطا در نوشتن!\n");
        return 1;
    }
    fwrite(&s1, sizeof(Student), 1, fp);
    fclose(fp);

    /* خواندن باینری */
    fp = fopen("student.bin", "rb");
    if (fp == NULL) {
        printf("خطا در خواندن!\n");
        return 1;
    }
    size_t read = fread(&s2, sizeof(Student), 1, fp);
    fclose(fp);

    if (read == 1) {
        printf("خوانده شد: %s، %d ساله، معدل %.2f\n", s2.name, s2.age, s2.gpa);
    } else {
        printf("خطا در خواندن!\n");
    }

    return 0;
}
