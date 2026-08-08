/*
 * تمرین ۱ — پیمایش دوطرفه
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
        head->prev = new_node;
    }
    return new_node;
}

void print_forward(Node *head) {
    for (Node *p = head; p != NULL; p = p->next) {
        printf("%d ", p->data);
    }
    printf("\n");
}

void print_backward(Node *head) {
    Node *tail = head;
    while (tail != NULL && tail->next != NULL) {
        tail = tail->next;
    }
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
    for (int i = 1; i <= 4; i++) {
        head = prepend(head, i * 10);
    }
    printf("رو به جلو: ");
    print_forward(head);
    printf("رو به عقب: ");
    print_backward(head);
    free_list(head);
    return 0;
}
