/*
 * تمرین ۱ — ساخت درخت و شمارش گره‌ها
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

int count_nodes(const Node *root) {
    if (root == NULL) {
        return 0;
    }
    return 1 + count_nodes(root->left) + count_nodes(root->right);
}

void free_tree(Node *root) {
    if (root == NULL) {
        return;
    }
    free_tree(root->left);
    free_tree(root->right);
    free(root);
}

int main(void) {
    Node *root = new_node(10);
    root->left = new_node(5);
    root->right = new_node(15);
    root->left->left = new_node(3);
    root->left->right = new_node(7);

    printf("تعداد گره‌ها: %d\n", count_nodes(root));
    free_tree(root);
    return 0;
}
