/*
 * جلسه ۳۸ — شمارش گره‌ها و ارتفاع درخت
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *left;
    struct Node *right;
} Node;

Node *new_node(int value) {
    Node *n = malloc(sizeof(Node));
    if (n == NULL) {
        return NULL;
    }
    n->data = value;
    n->left = NULL;
    n->right = NULL;
    return n;
}

int count_nodes(const Node *r) {
    if (r == NULL) {
        return 0;
    }
    return 1 + count_nodes(r->left) + count_nodes(r->right);
}

int height(const Node *r) {
    if (r == NULL) {
        return -1;   /* درخت خالی ارتفاع ندارد */
    }
    int left_h = height(r->left);
    int right_h = height(r->right);
    return (left_h > right_h ? left_h : right_h) + 1;
}

void free_tree(Node *r) {
    if (r == NULL) {
        return;
    }
    free_tree(r->left);
    free_tree(r->right);
    free(r);
}

int main(void) {
    Node *root = new_node(10);
    root->left = new_node(5);
    root->right = new_node(15);
    root->left->left = new_node(3);
    root->left->left->right = new_node(4);

    printf("تعداد گره‌ها: %d\n", count_nodes(root));   /* 5 */
    printf("ارتفاع: %d\n", height(root));               /* 3 */

    free_tree(root);
    return 0;
}
