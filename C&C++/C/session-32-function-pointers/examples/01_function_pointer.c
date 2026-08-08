/*
 * جلسه ۳۲ — اشاره‌گر به تابع: مبانی و callback
 */

#include <stdio.h>

int add(int a, int b) { return a + b; }
int sub(int a, int b) { return a - b; }
int mul(int a, int b) { return a * b; }

/* typedef برای خوانایی */
typedef int (*BinaryOp)(int, int);

/* تابعی که یک اشاره‌گر به تابع را به‌عنوان آرگومان می‌گیرد */
int apply_binary(BinaryOp op, int x, int y) {
    return op(x, y);   /* صدا زدن callback */
}

int main(void) {
    BinaryOp op = add;   /* آدرس تابع add */

    printf("op(3, 4) = %d\n", op(3, 4));

    /* تغییر اشاره‌گر به تابع دیگر */
    op = mul;
    printf("op(3, 4) = %d\n", op(3, 4));

    /* پاس دادن تابع به تابع دیگر */
    printf("apply_binary(sub, 10, 4) = %d\n", apply_binary(sub, 10, 4));
    printf("apply_binary(add, 10, 4) = %d\n", apply_binary(add, 10, 4));

    return 0;
}
