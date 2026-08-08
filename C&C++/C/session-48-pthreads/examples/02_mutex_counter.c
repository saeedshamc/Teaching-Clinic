/*
 * جلسه ۴۸ — حل رقابت (race condition) با mutex
 *
 * دو نخ هر کدام ۱,۰۰۰,۰۰۰ بار شمارندهٔ مشترک را افزایش می‌دهند.
 * بدون قفل نتیجه نادرست است؛ با قفل دقیقاً ۲,۰۰۰,۰۰۰ می‌شود.
 *
 * کامپایل:
 *   gcc -Wall -Wextra -pthread -o 02_mutex_counter 02_mutex_counter.c
 */

#include <pthread.h>
#include <stdio.h>

#define ITERATIONS 1000000

int counter = 0;                    /* متغیر مشترک */
pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;

/* افزایش بدون قفل — RACE! */
void *increment_unsafe(void *arg) {
    (void)arg;
    for (int i = 0; i < ITERATIONS; i++) {
        counter++;
    }
    return NULL;
}

/* افزایش با قفل — امن */
void *increment_safe(void *arg) {
    (void)arg;
    for (int i = 0; i < ITERATIONS; i++) {
        pthread_mutex_lock(&lock);
        counter++;
        pthread_mutex_unlock(&lock);
    }
    return NULL;
}

int main(void) {
    pthread_t t1, t2;

    /* بخش ۱: بدون قفل (نتیجهٔ نامشخص) */
    counter = 0;
    pthread_create(&t1, NULL, increment_unsafe, NULL);
    pthread_create(&t2, NULL, increment_unsafe, NULL);
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    printf("بدون قفل  : %d (انتظار: 2000000)\n", counter);

    /* بخش ۲: با قفل (نتیجهٔ دقیق) */
    counter = 0;
    pthread_create(&t1, NULL, increment_safe, NULL);
    pthread_create(&t2, NULL, increment_safe, NULL);
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    printf("با قفل    : %d (انتظار: 2000000)\n", counter);

    pthread_mutex_destroy(&lock);
    return 0;
}
