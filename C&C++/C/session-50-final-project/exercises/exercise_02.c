/*
 * تمرین ۲ — افزودن دستور search به دیتابیس فایلی
 *
 * دستور search <word> همهٔ رکوردهایی که شامل آن کلمه هستند را
 * پیدا و چاپ می‌کند.
 * دستورها: add <text> | list | search <word> | clear | exit
 *
 * کامپایل:
 *   gcc -Wall -Wextra -g -o exercise_02 exercise_02.c
 *   ./exercise_02
 */

#include <stdio.h>
#include <string.h>

#define DB_FILE "notes.txt"
#define MAX_LINE 256

void add_note(const char *text) {
    FILE *fp = fopen(DB_FILE, "a");
    if (fp == NULL) {
        fprintf(stderr, "خطا در باز کردن %s\n", DB_FILE);
        return;
    }
    fprintf(fp, "%s\n", text);
    fclose(fp);
    printf("ذخیره شد\n");
}

void list_notes(void) {
    FILE *fp = fopen(DB_FILE, "r");
    if (fp == NULL) {
        printf("هنوز یادداشتی وجود ندارد\n");
        return;
    }

    char line[MAX_LINE];
    int count = 0;
    while (fgets(line, sizeof(line), fp) != NULL) {
        count++;
        printf("%d. %s", count, line);
    }
    fclose(fp);

    if (count == 0) {
        printf("هنوز یادداشتی وجود ندارد\n");
    }
}

/* پیدا کردن رکوردهای شامل یک کلمه */
void search_notes(const char *word) {
    FILE *fp = fopen(DB_FILE, "r");
    if (fp == NULL) {
        printf("هنوز یادداشتی وجود ندارد\n");
        return;
    }

    char line[MAX_LINE];
    int count = 0;
    int found = 0;

    while (fgets(line, sizeof(line), fp) != NULL) {
        count++;
        /* strstr: آیا line شامل word است؟ (جلسهٔ ۱۴) */
        if (strstr(line, word) != NULL) {
            printf("%d. %s", count, line);
            found = 1;
        }
    }
    fclose(fp);

    if (!found) {
        printf("رکوردی شامل «%s» پیدا نشد\n", word);
    }
}

void clear_notes(void) {
    FILE *fp = fopen(DB_FILE, "w");
    if (fp == NULL) {
        fprintf(stderr, "خطا در باز کردن %s\n", DB_FILE);
        return;
    }
    fclose(fp);
    printf("همهٔ یادداشت‌ها حذف شد\n");
}

int main(void) {
    char line[MAX_LINE];

    printf("دیتابیس فایلی — دستورها: add <text> | list | search <word> | clear | exit\n");

    while (1) {
        printf("db> ");
        fflush(stdout);

        if (fgets(line, sizeof(line), stdin) == NULL) {
            printf("\n");
            break;
        }

        size_t len = strlen(line);
        if (len > 0 && line[len - 1] == '\n') {
            line[len - 1] = '\0';
        }

        if (strncmp(line, "add ", 4) == 0) {
            add_note(line + 4);
        } else if (strcmp(line, "list") == 0) {
            list_notes();
        } else if (strncmp(line, "search ", 7) == 0) {
            search_notes(line + 7);
        } else if (strcmp(line, "clear") == 0) {
            clear_notes();
        } else if (strcmp(line, "exit") == 0) {
            break;
        } else {
            printf("دستور ناشناخته\n");
        }
    }

    return 0;
}
