/*
 * تمرین ۲ — ذخیره و خواندن struct با fwrite/fread
 */

#include <stdio.h>
#include <string.h>

typedef struct {
    char name[50];
    int age;
} Person;

int main(void) {
    Person p1 = {"Sara", 22};
    Person p2;

    FILE *fp = fopen("person.bin", "wb");
    if (fp == NULL) {
        return 1;
    }
    fwrite(&p1, sizeof(Person), 1, fp);
    fclose(fp);

    fp = fopen("person.bin", "rb");
    if (fp == NULL) {
        return 1;
    }
    if (fread(&p2, sizeof(Person), 1, fp) == 1) {
        printf("خوانده شد: %s، %d ساله\n", p2.name, p2.age);
    }
    fclose(fp);
    return 0;
}
