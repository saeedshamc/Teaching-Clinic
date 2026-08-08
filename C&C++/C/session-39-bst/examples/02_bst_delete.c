/*
 * جلسه ۳۹ — حذف از BST (سه حالت)
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

Node *insert(Node *root, int value) {
    if (root == NULL) {
        return new_node(value);
    }
    if (value < root->data) {
        root->left = insert(root->left, value);
    } else {
        root->right = insert(root->right, value);
    }
    return root;
}

/* کوچک‌ترین گرهٔ زیردرخت */
Node *find_min(Node *root) {
    while (root != NULL && root->left != NULL) {
        root = root->left;
    }
    return root;
}

/* حذف گره با مقدار value */
Node *delete_node(Node *root, int value) {
    if (root == NULL) {
        return NULL;
    }

    if (value < root->data) {
        root->left = delete_node(root->left, value);
    } else if (value > root->data) {
        root->right = delete_node(root->right, value);
    } else {
        /* حالت ۱: برگ */
        if (root->left == NULL && root->right == NULL) {
            free(root);
            return NULL;
        }
        /* حالت ۲: یک فرزند */
        if (root->left == NULL) {
            Node *right = root->right;
            free(root);
            return right;
        }
        if (root->right == NULL) {
            Node *left = root->left;
            free(root);
            return left;
        }
        /* حالت ۳: دو فرزند — جانشین = کوچک‌ترین گرهٔ راست */
        Node *successor = find_min(root->right);
        root->data = successor->data;
        root->right = delete_node(root->right, successor->data);
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
    int values[] = {50, 30, 70, 20, 40, 60, 80};
    for (int i = 0; i < 7; i++) {
        root = insert(root, values[i]);
    }

    printf("قبل: ");
    print_inorder(root);
    printf("\n");

    root = delete_node(root, 20);   /* برگ */
    root = delete_node(root, 30);   /* یک فرزند */
    root = delete_node(root, 50);   /* دو فرزند */

    printf("بعد از حذف ۲۰، ۳۰، ۵۰: ");
    print_inorder(root);
    printf("\n");

    free_tree(root);
    return 0;
}
