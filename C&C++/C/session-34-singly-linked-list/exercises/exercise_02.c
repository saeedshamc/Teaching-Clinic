/*
 * تمرین ۲ — find و delete در لیست
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *next;
} Node;

Node *prepend(Node *head, int value) {
    Node *new_node = malloc(sizeof(Node));
    if (new_node == NULL) {
        return head;
    }
    new_node->data = value;
    new_node->next = head;
    return new_node;
}

Node *find_node(Node *head, int value) {
    for (Node *p = head; p != NULL; p = p->next) {
        if (p->data == value) {
            return p;
        }
    }
    return NULL;
}

Node *delete_value(Node *head, int value) {
    Node *prev = NULL;
    Node *cur = head;
    while (cur != NULL) {
        if (cur->data == value) {
            if (prev == NULL) {
                head = cur->next;
            } else {
                prev->next = cur->next;
            }
            free(cur);
            return head;
        }
        prev = cur;
        cur = cur->next;
    }
    return head;
}

void print_list(Node *head) {
    for (Node *p = head; p != NULL; p = p->next) {
        printf("%d -> ", p->data);
    }
    printf("NULL\n");
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

    printf("گرهٔ ۲۰: %s\n", find_node(head, 20) ? "یافت شد" : "یافت نشد");
    head = delete_value(head, 20);
    printf("بعد از حذف ۲۰: ");
    print_list(head);

    free_list(head);
    return 0;
}
