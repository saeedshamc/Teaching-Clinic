/*
 * تمرین ۲ — شمارنده با و بدون قفل
 *
 * دو نخ هر کدام ۱,۰۰۰,۰۰۰ بار شمارندهٔ مشترک را افزایش می‌دهند.
 * بدون قفل (عمداً نادرست) و با قفل (درست) و هر دو نتیجه چاپ می‌شود.
 *
 * کامپایل:
 *   gcc -Wall -Wextra -pthread -o exercise_02 exercise_02.c
 */

#include <pthread.h>
#include <stdio.h>

#define ITERATIONS 1000000

int counter = 0;
pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;

void *unsafe_inc(void *arg) {
    (void)arg;
    for (int i = 0; i < ITERATIONS; i++) {
        counter++;
    }
    return NULL;
}

void *safe_inc(void *arg) {
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

    counter = 0;
    pthread_create(&t1, NULL, unsafe_inc, NULL);
    pthread_create(&t2, NULL, unsafe_inc, NULL);
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    printf("بدون قفل: %d\n", counter);

    counter = 0;
    pthread_create(&t1, NULL, safe_inc, NULL);
    pthread_create(&t2, NULL, safe_inc, NULL);
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    printf("با قفل  : %d\n", counter);

    pthread_mutex_destroy(&lock);
    return 0;
}
