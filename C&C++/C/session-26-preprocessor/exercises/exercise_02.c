/*
 * تمرین ۲ — ماکرو MAX و کامپایل شرطی
 */

#include <stdio.h>

#define MAX(a, b) ((a) > (b) ? (a) : (b))
#define MAX3(a, b, c) MAX(MAX(a, b), c)

#ifdef DEBUG
#define LOG(x) printf("[دیباگ] %s\n", x)
#else
#define LOG(x) /* هیچ */
#endif

int main(void) {
    int a = 12, b = 7, c = 19;

    printf("بزرگ‌ترین: %d\n", MAX3(a, b, c));

    LOG("پایان برنامه");
    return 0;
}
