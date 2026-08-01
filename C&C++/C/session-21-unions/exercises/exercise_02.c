/*
 * تمرین ۲ — tagged union برای int یا float
 */

#include <stdio.h>

enum Kind { KIND_INT, KIND_FLOAT };

struct Value {
    enum Kind kind;
    union {
        int i;
        float f;
    } data;
};

void print_value(const struct Value *v) {
    switch (v->kind) {
        case KIND_INT:
            printf("int: %d\n", v->data.i);
            break;
        case KIND_FLOAT:
            printf("float: %f\n", v->data.f);
            break;
    }
}

int main(void) {
    struct Value v1 = {KIND_INT, .data.i = 100};
    struct Value v2 = {KIND_FLOAT, .data.f = 2.5f};

    print_value(&v1);
    print_value(&v2);
    return 0;
}
