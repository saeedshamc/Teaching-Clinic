/*
 * تمرین ۲ — ذخیره و بازخوانی نام و سن
 */

#include <stdio.h>

int main(void) {
    /* ذخیره */
    FILE *fp = fopen("people.txt", "w");
    if (fp == NULL) {
        printf("خطا!\n");
        return 1;
    }

    fprintf(fp, "%s %d\n", "Ali", 20);
    fprintf(fp, "%s %d\n", "Sara", 22);
    fprintf(fp, "%s %d\n", "Mina", 21);
    fclose(fp);

    /* بازخوانی */
    fp = fopen("people.txt", "r");
    if (fp == NULL) {
        printf("خطا!\n");
        return 1;
    }

    char name[50];
    int age;
    printf("افراد:\n");
    while (fscanf(fp, "%49s %d", name, &age) == 2) {
        printf("%s — %d ساله\n", name, age);
    }
    fclose(fp);
    return 0;
}
