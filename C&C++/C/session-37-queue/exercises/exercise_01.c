/*
 * تمرین ۱ — صف حلقوی پایه
 */

#include <stdio.h>
#include <stdbool.h>

#define MAX 4

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

void enqueue(Queue *q, int value) {
    if (q->count == MAX) {
        printf("پر! (%d رد شد)\n", value);
        return;
    }
    q->data[q->rear] = value;
    q->rear = (q->rear + 1) % MAX;
    q->count++;
}

int dequeue(Queue *q) {
    if (is_empty(q)) {
        return -1;
    }
    int v = q->data[q->front];
    q->front = (q->front + 1) % MAX;
    q->count--;
    return v;
}

int main(void) {
    Queue q;
    init(&q);

    enqueue(&q, 10);
    enqueue(&q, 20);
    enqueue(&q, 30);
    printf("dequeue: %d\n", dequeue(&q));
    enqueue(&q, 40);   /* باید جا داشته باشد چون یک دانه خارج شد */

    printf("باقی‌مانده: ");
    while (!is_empty(&q)) {
        printf("%d ", dequeue(&q));
    }
    printf("\n");

    return 0;
}
