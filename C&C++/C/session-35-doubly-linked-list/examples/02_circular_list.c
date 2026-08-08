/*
 * جلسه ۳۵ — لیست حلقوی: آخرین گره به اول وصل می‌شود
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *next;
} Node;

/* ساخت لیست حلقوی از یک لیست معمولی: tail->next = head */
Node *make_circular(Node *head) {
    if (head == NULL) {
        return NULL;
    }
    Node *tail = head;
    while (tail->next != NULL) {
        tail = tail->next;
    }
    tail->next = head;   /* حلقه بستن */
    return head;
}

/* چاپ n گره از head — چون حلقه است، بی‌نهایت است */
void print_n(Node *head, int n) {
    Node *p = head;
    for (int i = 0; i < n; i++) {
        printf("%d ", p->data);
        p = p->next;
    }
    printf("... (حلقه)\n");
}

int main(void) {
    Node *a = malloc(sizeof(Node));
    Node *b = malloc(sizeof(Node));
    Node *c = malloc(sizeof(Node));

    if (a == NULL || b == NULL || c == NULL) {
        return 1;
    }

    a->data = 1;
    b->data = 2;
    c->data = 3;
    a->next = b;
    b->next = c;
    c->next = NULL;

    Node *head = make_circular(a);
    print_n(head, 7);   /* 1 2 3 1 2 3 1 ... */

    /* آزادسازی حلقوی: باید از جایی بشکنیم */
    c->next = NULL;   /* باز کردن حلقه */
    free(c);
    free(b);
    free(a);
    return 0;
}
