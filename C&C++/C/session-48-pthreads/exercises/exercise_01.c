/*
 * تمرین ۱ — چهار نخ که شمارهٔ خودشان را چاپ می‌کنند
 *
 * کامپایل:
 *   gcc -Wall -Wextra -pthread -o exercise_01 exercise_01.c
 */

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int id;
    char name[20];
} ThreadArg;

void *print_worker(void *arg) {
    ThreadArg *a = (ThreadArg *)arg;
    printf("نخ %d (%s) در حال اجراست\n", a->id, a->name);
    return NULL;
}

int main(void) {
    pthread_t threads[4];
    ThreadArg args[4];
    const char *names[4] = {"ali", "sara", "reza", "mina"};

    for (int i = 0; i < 4; i++) {
        args[i].id = i + 1;
        snprintf(args[i].name, sizeof(args[i].name), "%s", names[i]);

        int rc = pthread_create(&threads[i], NULL, print_worker, &args[i]);
        if (rc != 0) {
            fprintf(stderr, "خطا در ساخت نخ %d\n", i);
            return 1;
        }
    }

    for (int i = 0; i < 4; i++) {
        pthread_join(threads[i], NULL);
    }

    printf("هر چهار نخ تمام شدند\n");
    return 0;
}
