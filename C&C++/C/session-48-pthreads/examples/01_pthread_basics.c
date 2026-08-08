/*
 * جلسه ۴۸ — ساخت و join نخ‌ها (پایه)
 *
 * کامپایل:
 *   gcc -Wall -Wextra -pthread -o 01_pthread_basics 01_pthread_basics.c
 */

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

/* تابع نخ: آرگومان، آدرس یک int است */
void *worker(void *arg) {
    int id = *(int *)arg;
    printf("نخ %d شروع شد\n", id);
    return NULL;
}

int main(void) {
    pthread_t threads[3];
    int ids[3] = {1, 2, 3};

    /* ساخت ۳ نخ */
    for (int i = 0; i < 3; i++) {
        int rc = pthread_create(&threads[i], NULL, worker, &ids[i]);
        if (rc != 0) {
            fprintf(stderr, "خطا در ساخت نخ %d\n", i);
            return 1;
        }
    }

    /* منتظر پایان همهٔ نخ‌ها */
    for (int i = 0; i < 3; i++) {
        pthread_join(threads[i], NULL);
    }

    printf("همهٔ نخ‌ها تمام شدند\n");
    return 0;
}
