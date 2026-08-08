/*
 * جلسه ۳۶ — بررسی تعادل پرانتزها با پشته
 */

#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define MAX_SIZE 100

typedef struct {
    char data[MAX_SIZE];
    int top;
} CharStack;

void init(CharStack *s) {
    s->top = -1;
}

bool is_empty(const CharStack *s) {
    return s->top == -1;
}

bool push(CharStack *s, char c) {
    if (s->top >= MAX_SIZE - 1) {
        return false;
    }
    s->top++;
    s->data[s->top] = c;
    return true;
}

char pop(CharStack *s) {
    if (is_empty(s)) {
        return '\0';
    }
    char c = s->data[s->top];
    s->top--;
    return c;
}

/* بررسی: هر پرانتز باز باید با بستهٔ متناظر بسته شود */
bool is_balanced(const char *str) {
    CharStack s;
    init(&s);

    for (int i = 0; str[i] != '\0'; i++) {
        char c = str[i];
        if (c == '(' || c == '[' || c == '{') {
            if (!push(&s, c)) {
                return false;
            }
        } else if (c == ')' || c == ']' || c == '}') {
            char open = pop(&s);
            if ((c == ')' && open != '(') ||
                (c == ']' && open != '[') ||
                (c == '}' && open != '{')) {
                return false;
            }
        }
    }
    return is_empty(&s);
}

int main(void) {
    const char *tests[] = {"(a+b)", "((a+b)*c)", "([)]", "(())"};

    for (int i = 0; i < 4; i++) {
        printf("%-10s → %s\n", tests[i],
               is_balanced(tests[i]) ? "متوازن" : "نامتوازن");
    }

    return 0;
}
