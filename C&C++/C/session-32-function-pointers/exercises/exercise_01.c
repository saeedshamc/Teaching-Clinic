/*
 * تمرین ۱ — add/sub/mul با اشاره‌گر به تابع
 */

#include <stdio.h>

int add(int a, int b) { return a + b; }
int sub(int a, int b) { return a - b; }
int mul(int a, int b) { return a * b; }

typedef int (*BinaryOp)(int, int);

int main(void) {
    BinaryOp ops[3] = {add, sub, mul};
    const char *names[3] = {"add", "sub", "mul"};

    for (int i = 0; i < 3; i++) {
        printf("%s(10, 4) = %d\n", names[i], ops[i](10, 4));
    }
    return 0;
}
