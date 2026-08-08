/*
 * جلسه ۳۸ — ساخت درخت دودویی و پیمایش‌ها
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

void preorder(const Node *r) {
    if (r == NULL) {
        return;
    }
    printf("%d ", r->data);
    preorder(r->left);
    preorder(r->right);
}

void inorder(const Node *r) {
    if (r == NULL) {
        return;
    }
    inorder(r->left);
    printf("%d ", r->data);
    inorder(r->right);
}

void postorder(const Node *r) {
    if (r == NULL) {
        return;
    }
    postorder(r->left);
    postorder(r->right);
    printf("%d ", r->data);
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
    /* درخت:
     *        1
     *       / \
     *      2   3
     *     / \
     *    4   5
     */
    Node *root = new_node(1);
    root->left = new_node(2);
    root->right = new_node(3);
    root->left->left = new_node(4);
    root->left->right = new_node(5);

    printf("پیشوندی (preorder):  ");
    preorder(root);
    printf("\n");

    printf("میانوندی (inorder):  ");
    inorder(root);
    printf("\n");

    printf("پسوندی (postorder):  ");
    postorder(root);
    printf("\n");

    free_tree(root);
    return 0;
}
