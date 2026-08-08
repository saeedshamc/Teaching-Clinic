/*
 * تمرین ۲ — BST: محاسبهٔ مجموع عناصر
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *left;
    struct Node *right;
} Node;

Node *insert(Node *root, int value) {
    if (root == NULL) {
        Node *n = malloc(sizeof(Node));
        if (n == NULL) {
            return NULL;
        }
        n->data = value;
        n->left = NULL;
        n->right = NULL;
        return n;
    }
    if (value < root->data) {
        root->left = insert(root->left, value);
    } else {
        root->right = insert(root->right, value);
    }
    return root;
}

int sum_tree(const Node *root) {
    if (root == NULL) {
        return 0;
    }
    return root->data + sum_tree(root->left) + sum_tree(root->right);
}

void print_inorder(const Node *root) {
    if (root == NULL) {
        return;
    }
    print_inorder(root->left);
    printf("%d ", root->data);
    print_inorder(root->right);
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
    Node *root = NULL;
    int values[] = {50, 30, 70, 20, 40, 60, 80};

    for (int i = 0; i < 7; i++) {
        root = insert(root, values[i]);
    }

    printf("پیمایش مرتب: ");
    print_inorder(root);
    printf("\n");

    printf("مجموع: %d\n", sum_tree(root));   /* 350 */
    free_tree(root);
    return 0;
}
