/*
 * جلسه ۴۳ — جدول هش با زنجیره‌سازی (chaining)
 * هر خانه یک لیست پیوندی از کلیدهاست
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TABLE_SIZE 7

/* گرهٔ لیست پیوندی برای یک کلید */
typedef struct Node {
    char key[50];
    struct Node *next;
} Node;

/* جدول هش: آرایه‌ای از اشاره‌گر به ابتدای لیست‌ها */
Node *table[TABLE_SIZE] = {NULL};

/* تابع هش */
unsigned hash(const char *key) {
    unsigned h = 0;
    for (int i = 0; key[i] != '\0'; i++) {
        h = h * 31 + (unsigned char)key[i];
    }
    return h % TABLE_SIZE;
}

/* افزودن کلید (در صورت تکراری بودن، کاری نمی‌کنیم) */
void insert(const char *key) {
    unsigned idx = hash(key);

    /* بررسی تکراری نبودن */
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
    new_node->next = table[idx];   /* به ابتدای لیست اضافه می‌کنیم */
    table[idx] = new_node;
}

/* جستجوی کلید */
int search(const char *key) {
    unsigned idx = hash(key);
    for (Node *cur = table[idx]; cur != NULL; cur = cur->next) {
        if (strcmp(cur->key, key) == 0) {
            return 1;   /* پیدا شد */
        }
    }
    return 0;
}

/* آزادسازی کامل جدول */
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

/* نمایش جدول */
void print_table(void) {
    for (int i = 0; i < TABLE_SIZE; i++) {
        printf("[%d]", i);
        for (Node *cur = table[i]; cur != NULL; cur = cur->next) {
            printf(" -> %s", cur->key);
        }
        printf("\n");
    }
}

int main(void) {
    const char *names[] = {"ali", "sara", "reza", "mina", "navid", "hani"};
    int count = (int)(sizeof(names) / sizeof(names[0]));

    for (int i = 0; i < count; i++) {
        insert(names[i]);
    }

    printf("محتوای جدول:\n");
    print_table();

    printf("\nجستجو:\n");
    printf("  sara -> %s\n", search("sara") ? "پیدا شد" : "پیدا نشد");
    printf("  amir -> %s\n", search("amir") ? "پیدا شد" : "پیدا نشد");

    free_table();   /* جلوگیری از memory leak */
    return 0;
}
