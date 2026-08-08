/*
 * تمرین ۲ — حذف گره از لیست دوطرفه
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

Node *delete_node(Node *head, Node *node) {
    if (node == NULL) {
        return head;
    }
    if (node->prev != NULL) {
        node->prev->next = node->next;
    } else {
        head = node->next;   /* حذف head */
    }
    if (node->next != NULL) {
        node->next->prev = node->prev;
    }
    free(node);
    return head;
}

void print_forward(Node *head) {
    for (Node *p = head; p != NULL; p = p->next) {
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
    head = prepend(head, 40);

    printf("قبل: ");
    print_forward(head);

    /* حذف گرهٔ ۲۰ */
    Node *to_delete = NULL;
    for (Node *p = head; p != NULL; p = p->next) {
        if (p->data == 20) {
            to_delete = p;
            break;
        }
    }
    head = delete_node(head, to_delete);

    printf("بعد: ");
    print_forward(head);

    free_list(head);
    return 0;
}
