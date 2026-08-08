/*
 * تمرین ۲ — شمارندهٔ تکرار کلمات یک جمله با جدول هش
 * (word frequency counter)
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TABLE_SIZE 31
#define MAX_WORD 50

typedef struct Node {
    char word[MAX_WORD];
    int count;               /* تعداد تکرار */
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

/* افزودن/افزایش کلمه */
void add_word(const char *word) {
    unsigned idx = hash(word);

    for (Node *cur = table[idx]; cur != NULL; cur = cur->next) {
        if (strcmp(cur->word, word) == 0) {
            cur->count++;          /* کلمه قبلاً دیده شده */
            return;
        }
    }

    Node *new_node = (Node *)malloc(sizeof(Node));
    if (new_node == NULL) {
        fprintf(stderr, "خطای تخصیص حافظه\n");
        return;
    }
    strcpy(new_node->word, word);
    new_node->count = 1;
    new_node->next = table[idx];
    table[idx] = new_node;
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
    /* جملهٔ نمونه — کلمات با فاصله جدا شده‌اند */
    const char *sentence = "the cat and the dog and the bird";
    char buffer[MAX_WORD];
    int buf_len = 0;

    /* جداسازی کلمات با فاصله */
    for (int i = 0; sentence[i] != '\0'; i++) {
        if (sentence[i] == ' ') {
            if (buf_len > 0) {
                buffer[buf_len] = '\0';
                add_word(buffer);
                buf_len = 0;
            }
        } else {
            buffer[buf_len++] = sentence[i];
        }
    }
    if (buf_len > 0) {
        buffer[buf_len] = '\0';
        add_word(buffer);
    }

    /* نمایش تعداد تکرارها */
    printf("تکرار کلمات:\n");
    for (int i = 0; i < TABLE_SIZE; i++) {
        for (Node *cur = table[i]; cur != NULL; cur = cur->next) {
            printf("  %-10s : %d\n", cur->word, cur->count);
        }
    }

    free_table();
    return 0;
}
