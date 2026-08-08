/*
 * تمرین ۲ — محاسبهٔ ارتفاع درخت
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

/* ارتفاع: بیشترین تعداد یال از ریشه تا عمیق‌ترین برگ */
int height(const Node *root) {
    if (root == NULL) {
        return -1;   /* درخت خالی */
    }
    int left = height(root->left);
    int right = height(root->right);
    return (left > right ? left : right) + 1;
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
    Node *root = new_node(1);
    root->left = new_node(2);
    root->right = new_node(3);
    root->left->left = new_node(4);
    root->left->left->right = new_node(5);

    printf("ارتفاع درخت: %d\n", height(root));   /* 3 */
    free_tree(root);
    return 0;
}
