/*
 * تمرین ۱ — جدول هش با زنجیره‌سازی برای ۵ کلید
 * یک جستجوی موفق و یک جستجوی ناموفق
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TABLE_SIZE 5

typedef struct Node {
    char key[50];
    struct Node *next;
} Node;

Node *table[TABLE_SIZE] = {NULL};

unsigned hash(const char *key) {
    unsigned h = 0;
    for (int i = 0; key[i] != '\0'; i++) {
        h = h * 31 + (unsigned char)key[i];
    }
    return h % TABLE_SIZE;
}

void insert(const char *key) {
    unsigned idx = hash(key);

    for (Node *cur = table[idx]; cur != NULL; cur = cur->next) {
        if (strcmp(cur->key, key) == 0) {
            return;
        }
    }

    Node *new_node = (Node *)malloc(sizeof(Node));
    if (new_node == NULL) {
        fprintf(stderr, "خطای تخصیص حافظه\n");
        return;
    }
    strcpy(new_node->key, key);
    new_node->next = table[idx];
    table[idx] = new_node;
}

int search(const char *key) {
    unsigned idx = hash(key);
    for (Node *cur = table[idx]; cur != NULL; cur = cur->next) {
        if (strcmp(cur->key, key) == 0) {
            return 1;
        }
    }
    return 0;
}

void free_table(void) {
    for (int i = 0; i < TABLE_SIZE; i++) {
        Node *cur = table[i];
        while (cur != NULL) {
            Node *next = cur->next;
            free(cur);
            cur = next;
        }
        table[i] = NULL;
    }
}

int main(void) {
    const char *keys[] = {"apple", "banana", "cherry", "date", "elder"};
    int count = (int)(sizeof(keys) / sizeof(keys[0]));

    for (int i = 0; i < count; i++) {
        insert(keys[i]);
    }

    /* جستجوی موفق */
    printf("banana: %s\n", search("banana") ? "پیدا شد" : "پیدا نشد");

    /* جستجوی ناموفق */
    printf("grape : %s\n", search("grape") ? "پیدا شد" : "پیدا نشد");

    free_table();
    return 0;
}
