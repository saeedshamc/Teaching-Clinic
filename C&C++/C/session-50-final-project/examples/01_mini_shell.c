/*
 * جلسه ۵۰ — پروژهٔ A: مینیشل ساده
 *
 * یک شل مینیمال: دستورها را می‌خواند و با fork/execvp اجرا می‌کند.
 * دستور داخلی: exit
 *
 * کامپایل (لینوکس/WSL):
 *   gcc -Wall -Wextra -g -o 01_mini_shell 01_mini_shell.c
 *   ./01_mini_shell
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <unistd.h>

#define MAX_LINE 256
#define MAX_ARGS 32

int main(void) {
    char line[MAX_LINE];

    while (1) {
        printf("mini-sh> ");
        fflush(stdout);   /* پرامپت حتماً چاپ شود */

        /* پایان ورودی (Ctrl+D) → خروج */
        if (fgets(line, sizeof(line), stdin) == NULL) {
            printf("\n");
            break;
        }

        /* توکن‌بندی بر اساس فاصله و newline */
        char *args[MAX_ARGS];
        int argc = 0;
        char *token = strtok(line, " \n");
        while (token != NULL && argc < MAX_ARGS - 1) {
            args[argc++] = token;
            token = strtok(NULL, " \n");
        }
        args[argc] = NULL;

        if (argc == 0) {
            continue;               /* خط خالی */
        }

        /* دستور داخلی */
        if (strcmp(args[0], "exit") == 0) {
            break;
        }

        /* اجرای دستور در یک فرایند فرزند */
        pid_t pid = fork();
        if (pid < 0) {
            perror("fork");
        } else if (pid == 0) {
            execvp(args[0], args);   /* جایگزینی فرایند فرزند */
            perror("execvp");        /* اگر دستور ناشناخته باشد */
            exit(EXIT_FAILURE);
        } else {
            wait(NULL);              /* منتظر پایان فرزند */
        }
    }

    return 0;
}
