/*
 * جلسه ۳۴ — لیست پیوندی یک‌طرفه: ساخت، درج ابتدا، چاپ، آزادسازی
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *next;
} Node;

/* درج در ابتدای لیست */
Node *prepend(Node *head, int value) {
    Node *new_node = malloc(sizeof(Node));
    if (new_node == NULL) {
        printf("تخصیص حافظه ناموفق!\n");
        return head;
    }
    new_node->data = value;
    new_node->next = head;
    return new_node;   /* head جدید */
}

/* چاپ لیست */
void print_list(Node *head) {
    for (Node *p = head; p != NULL; p = p->next) {
        printf("%d -> ", p->data);
    }
    printf("NULL\n");
}

/* آزادسازی کامل */
void free_list(Node *head) {
    while (head != NULL) {
        Node *next = head->next;   /* اول ذخیره کن */
        free(head);                /* بعد آزاد کن */
        head = next;
    }
}

int main(void) {
    Node *head = NULL;   /* لیست خالی */

    head = prepend(head, 10);
    head = prepend(head, 20);
    head = prepend(head, 30);
    head = prepend(head, 40);

    printf("لیست: ");
    print_list(head);

    free_list(head);
    return 0;
}
