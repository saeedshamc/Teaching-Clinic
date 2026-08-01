/*
 * جلسه ۲۱ — union با tag: الگوی «نوع پویا»
 */

#include <stdio.h>

enum Kind { KIND_INT, KIND_FLOAT };

struct Value {
    enum Kind kind;          /* tag: مشخص می‌کند کدام عضو معتبر است */
    union {
        int i;
        float f;
    } data;
};

void print_value(const struct Value *v) {
    if (v->kind == KIND_INT) {
        printf("عدد صحیح: %d\n", v->data.i);
    } else {
        printf("عدد اعشاری: %f\n", v->data.f);
    }
}

int main(void) {
    struct Value a = {KIND_INT, .data.i = 42};
    struct Value b = {KIND_FLOAT, .data.f = 3.14f};

    print_value(&a);
    print_value(&b);

    return 0;
}
