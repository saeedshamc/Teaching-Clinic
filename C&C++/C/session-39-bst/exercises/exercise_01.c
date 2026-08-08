/*
 * تمرین ۱ — BST: درج و پیمایش مرتب
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
    int values[] = {8, 3, 10, 1, 6, 14, 4, 7, 13};

    for (int i = 0; i < 9; i++) {
        root = insert(root, values[i]);
    }

    printf("مرتب: ");
    print_inorder(root);
    printf("\n");

    free_tree(root);
    return 0;
}
