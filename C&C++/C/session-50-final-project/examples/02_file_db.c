/*
 * جلسه ۵۰ — پروژهٔ B: پایگاه‌دادهٔ فایلی ساده
 *
 * یک برنامهٔ نوت‌برداری که رکوردها را در notes.txt نگه می‌دارد.
 * دستورها: add <text> | list | clear | exit
 *
 * کامپایل:
 *   gcc -Wall -Wextra -g -o 02_file_db 02_file_db.c
 *   ./02_file_db
 */

#include <stdio.h>
#include <string.h>

#define DB_FILE "notes.txt"
#define MAX_LINE 256

/* افزودن یک رکورد به انتهای فایل */
void add_note(const char *text) {
    FILE *fp = fopen(DB_FILE, "a");   /* حالت append */
    if (fp == NULL) {
        fprintf(stderr, "خطا در باز کردن %s\n", DB_FILE);
        return;
    }
    fprintf(fp, "%s\n", text);
    fclose(fp);
    printf("ذخیره شد\n");
}

/* نمایش همهٔ رکوردها */
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

/* پاک کردن همهٔ رکوردها */
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

    printf("دیتابیس فایلی — دستورها: add <text> | list | clear | exit\n");

    while (1) {
        printf("db> ");
        fflush(stdout);

        if (fgets(line, sizeof(line), stdin) == NULL) {
            printf("\n");
            break;
        }

        /* حذف newline انتهایی */
        size_t len = strlen(line);
        if (len > 0 && line[len - 1] == '\n') {
            line[len - 1] = '\0';
        }

        if (strncmp(line, "add ", 4) == 0) {
            add_note(line + 4);               /* متن بعد از "add " */
        } else if (strcmp(line, "list") == 0) {
            list_notes();
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
