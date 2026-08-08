/*
 * تمرین ۱ — افزودن دستور داخلی echo به مینیشل
 *
 * دستور echo بدون fork اجرا می‌شود و بقیهٔ آرگومان‌ها را چاپ می‌کند.
 * مثال: mini-sh> echo سلام دنیا
 * خروجی: سلام دنیا
 *
 * کامپایل (لینوکس/WSL):
 *   gcc -Wall -Wextra -g -o exercise_01 exercise_01.c
 *   ./exercise_01
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
        fflush(stdout);

        if (fgets(line, sizeof(line), stdin) == NULL) {
            printf("\n");
            break;
        }

        char *args[MAX_ARGS];
        int argc = 0;
        char *token = strtok(line, " \n");
        while (token != NULL && argc < MAX_ARGS - 1) {
            args[argc++] = token;
            token = strtok(NULL, " \n");
        }
        args[argc] = NULL;

        if (argc == 0) {
            continue;
        }

        /* دستورهای داخلی */
        if (strcmp(args[0], "exit") == 0) {
            break;
        }

        if (strcmp(args[0], "echo") == 0) {
            /* چاپ بقیهٔ آرگومان‌ها بدون اجرای فرایند جدید */
            for (int i = 1; i < argc; i++) {
                if (i > 1) {
                    printf(" ");
                }
                printf("%s", args[i]);
            }
            printf("\n");
            continue;
        }

        /* دستورهای خارجی */
        pid_t pid = fork();
        if (pid < 0) {
            perror("fork");
        } else if (pid == 0) {
            execvp(args[0], args);
            perror("execvp");
            exit(EXIT_FAILURE);
        } else {
            wait(NULL);
        }
    }

    return 0;
}
