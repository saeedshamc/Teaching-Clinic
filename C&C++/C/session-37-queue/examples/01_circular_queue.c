/*
 * جلسه ۳۷ — صف حلقوی با آرایه
 */

#include <stdio.h>
#include <stdbool.h>

#define MAX 5

typedef struct {
    int data[MAX];
    int front;
    int rear;
    int count;
} Queue;

void init(Queue *q) {
    q->front = 0;
    q->rear = 0;
    q->count = 0;
}

bool is_empty(const Queue *q) {
    return q->count == 0;
}

bool is_full(const Queue *q) {
    return q->count == MAX;
}

void enqueue(Queue *q, int value) {
    if (is_full(q)) {
        printf("صف پر است! (%d)\n", value);
        return;
    }
    q->data[q->rear] = value;
    q->rear = (q->rear + 1) % MAX;   /* دور زدن */
    q->count++;
}

int dequeue(Queue *q) {
    if (is_empty(q)) {
        printf("صف خالی است!\n");
        return -1;
    }
    int value = q->data[q->front];
    q->front = (q->front + 1) % MAX;
    q->count--;
    return value;
}

int main(void) {
    Queue q;
    init(&q);

    enqueue(&q, 10);
    enqueue(&q, 20);
    enqueue(&q, 30);
    enqueue(&q, 40);
    enqueue(&q, 50);
    enqueue(&q, 60);   /* صف پر است */

    printf("dequeue: %d\n", dequeue(&q));
    printf("dequeue: %d\n", dequeue(&q));

    enqueue(&q, 60);   /* فضای خالی شد — دوباره کار می‌کند */
    enqueue(&q, 70);

    printf("باقی‌مانده: ");
    while (!is_empty(&q)) {
        printf("%d ", dequeue(&q));
    }
    printf("\n");

    return 0;
}
