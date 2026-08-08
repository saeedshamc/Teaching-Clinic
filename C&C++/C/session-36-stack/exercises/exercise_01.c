/*
 * تمرین ۱ — push و pop پایه
 */

#include <stdio.h>
#include <stdbool.h>

#define MAX_SIZE 100

typedef struct {
    int data[MAX_SIZE];
    int top;
} Stack;

void init(Stack *s) {
    s->top = -1;
}

bool is_empty(const Stack *s) {
    return s->top == -1;
}

void push(Stack *s, int value) {
    if (s->top >= MAX_SIZE - 1) {
        printf("پر!\n");
        return;
    }
    s->top++;
    s->data[s->top] = value;
}

int pop(Stack *s) {
    if (is_empty(s)) {
        return -1;
    }
    int v = s->data[s->top];
    s->top--;
    return v;
}

int main(void) {
    Stack s;
    init(&s);

    for (int i = 1; i <= 5; i++) {
        push(&s, i * 10);
    }

    printf("خروجی (معکوس): ");
    while (!is_empty(&s)) {
        printf("%d ", pop(&s));
    }
    printf("\n");

    return 0;
}
