/*
 * تمرین ۲ — صف با لیست پیوندی
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *next;
} Node;

typedef struct {
    Node *front;
    Node *rear;
} Queue;

void init(Queue *q) {
    q->front = NULL;
    q->rear = NULL;
}

int is_empty(const Queue *q) {
    return q->front == NULL;
}

void enqueue(Queue *q, int value) {
    Node *n = malloc(sizeof(Node));
    if (n == NULL) {
        return;
    }
    n->data = value;
    n->next = NULL;
    if (q->rear == NULL) {
        q->front = n;
    } else {
        q->rear->next = n;
    }
    q->rear = n;
}

int dequeue(Queue *q) {
    if (is_empty(q)) {
        return -1;
    }
    Node *old = q->front;
    int v = old->data;
    q->front = old->next;
    if (q->front == NULL) {
        q->rear = NULL;
    }
    free(old);
    return v;
}

int main(void) {
    Queue q;
    init(&q);

    enqueue(&q, 5);
    enqueue(&q, 15);
    enqueue(&q, 25);

    printf("dequeue: %d\n", dequeue(&q));
    printf("باقی‌مانده: ");
    while (!is_empty(&q)) {
        printf("%d ", dequeue(&q));
    }
    printf("\n");

    return 0;
}
