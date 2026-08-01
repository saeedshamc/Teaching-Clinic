/*
 * تمرین ۱ — نوشتن سه خط و بازخوانی
 */

#include <stdio.h>

int main(void) {
    FILE *fp = fopen("notes.txt", "w");
    if (fp == NULL) {
        printf("خطا!\n");
        return 1;
    }

    fprintf(fp, "یادداشت اول\n");
    fprintf(fp, "یادداشت دوم\n");
    fprintf(fp, "یادداشت سوم\n");
    fclose(fp);

    fp = fopen("notes.txt", "r");
    if (fp == NULL) {
        printf("خطا!\n");
        return 1;
    }

    char line[128];
    printf("محتویات:\n");
    while (fgets(line, sizeof(line), fp) != NULL) {
        printf("%s", line);
    }
    fclose(fp);
    return 0;
}
