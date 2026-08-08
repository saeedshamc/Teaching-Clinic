/*
 * جلسه ۳۷ — صف با لیست پیوندی (head = front، tail = rear)
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *next;
} Node;

typedef struct {
    Node *front;   /* ابتدای صف — dequeue از اینجا */
    Node *rear;    /* انتهای صف — enqueue از اینجا */
} Queue;

void init(Queue *q) {
    q->front = NULL;
    q->rear = NULL;
}

int is_empty(const Queue *q) {
    return q->front == NULL;
}

void enqueue(Queue *q, int value) {
    Node *new_node = malloc(sizeof(Node));
    if (new_node == NULL) {
        printf("تخصیص ناموفق!\n");
        return;
    }
    new_node->data = value;
    new_node->next = NULL;

    if (q->rear == NULL) {
        q->front = new_node;   /* صف خالی بود */
    } else {
        q->rear->next = new_node;
    }
    q->rear = new_node;
}

int dequeue(Queue *q) {
    if (is_empty(q)) {
        printf("صف خالی است!\n");
        return -1;
    }
    Node *old = q->front;
    int value = old->data;
    q->front = old->next;
    if (q->front == NULL) {
        q->rear = NULL;   /* صف خالی شد */
    }
    free(old);
    return value;
}

void free_queue(Queue *q) {
    while (!is_empty(q)) {
        dequeue(q);
    }
}

int main(void) {
    Queue q;
    init(&q);

    enqueue(&q, 1);
    enqueue(&q, 2);
    enqueue(&q, 3);

    printf("dequeue: %d\n", dequeue(&q));
    enqueue(&q, 4);

    printf("باقی‌مانده: ");
    while (!is_empty(&q)) {
        printf("%d ", dequeue(&q));
    }
    printf("\n");

    free_queue(&q);
    return 0;
}
