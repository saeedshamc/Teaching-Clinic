/*
 * جلسه ۳۹ — درخت جستجوی دودویی: درج و جستجو
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *left;
    struct Node *right;
} Node;

/* درج: کوچک‌ترها چپ، بزرگ‌ترها راست */
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

/* جستجو: NULL یعنی پیدا نشد */
Node *search(Node *root, int value) {
    if (root == NULL || root->data == value) {
        return root;
    }
    if (value < root->data) {
        return search(root->left, value);
    }
    return search(root->right, value);
}

/* inorder خروجی مرتب می‌دهد */
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

    printf("جستجوی ۴۰: %s\n", search(root, 40) ? "یافت شد" : "یافت نشد");
    printf("جستجوی ۹۹: %s\n", search(root, 99) ? "یافت شد" : "یافت نشد");

    free_tree(root);
    return 0;
}
