/*
 * جلسه ۳۶ — پشته با آرایه
 */

#include <stdio.h>
#include <stdbool.h>

#define MAX_SIZE 100

typedef struct {
    int data[MAX_SIZE];
    int top;
} Stack;

void init(Stack *s) {
    s->top = -1;   /* پشتهٔ خالی */
}

bool is_empty(const Stack *s) {
    return s->top == -1;
}

bool is_full(const Stack *s) {
    return s->top == MAX_SIZE - 1;
}

void push(Stack *s, int value) {
    if (is_full(s)) {
        printf("پشته پر است!\n");
        return;
    }
    s->top++;
    s->data[s->top] = value;
}

int pop(Stack *s) {
    if (is_empty(s)) {
        printf("پشته خالی است!\n");
        return -1;
    }
    int value = s->data[s->top];
    s->top--;
    return value;
}

int peek(const Stack *s) {
    if (is_empty(s)) {
        return -1;
    }
    return s->data[s->top];
}

int main(void) {
    Stack s;
    init(&s);

    push(&s, 10);
    push(&s, 20);
    push(&s, 30);

    printf("بالا (peek): %d\n", peek(&s));

    printf("pop: ");
    while (!is_empty(&s)) {
        printf("%d ", pop(&s));
    }
    printf("\n");

    return 0;
}
