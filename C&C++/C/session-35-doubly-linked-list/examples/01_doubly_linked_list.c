/*
 * جلسه ۳۵ — لیست پیوندی دوطرفه
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *prev;
    struct Node *next;
} Node;

Node *prepend(Node *head, int value) {
    Node *new_node = malloc(sizeof(Node));
    if (new_node == NULL) {
        return head;
    }
    new_node->data = value;
    new_node->prev = NULL;
    new_node->next = head;
    if (head != NULL) {
        head->prev = new_node;   /* به‌روزرسانی prev گرهٔ اول */
    }
    return new_node;
}

void print_forward(Node *head) {
    printf("رو به جلو: ");
    for (Node *p = head; p != NULL; p = p->next) {
        printf("%d ", p->data);
    }
    printf("\n");
}

void print_backward(Node *head) {
    /* اول برو به انتها */
    Node *tail = head;
    while (tail != NULL && tail->next != NULL) {
        tail = tail->next;
    }
    printf("رو به عقب: ");
    for (Node *p = tail; p != NULL; p = p->prev) {
        printf("%d ", p->data);
    }
    printf("\n");
}

void free_list(Node *head) {
    while (head != NULL) {
        Node *next = head->next;
        free(head);
        head = next;
    }
}

int main(void) {
    Node *head = NULL;
    head = prepend(head, 10);
    head = prepend(head, 20);
    head = prepend(head, 30);

    print_forward(head);
    print_backward(head);

    free_list(head);
    return 0;
}
