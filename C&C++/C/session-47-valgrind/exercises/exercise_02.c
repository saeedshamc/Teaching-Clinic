/*
 * تمرین ۲ — نشت در مسیر خطا
 *
 * اگر مقدار n نامعتبر باشد (مثلاً n <= 0)، تابع با return زودهنگام
 * خارج می‌شود و حافظهٔ قبلاً تخصیص‌داده‌شده آزاد نمی‌شود.
 * با valgrind نشت را ببینید و با آزادسازی قبل از return آن را رفع کنید.
 */

#include <stdio.h>
#include <stdlib.h>

int process(int n) {
    /* اندازهٔ تخصیص مستقل از اعتبار n است تا مسیر خطا با حافظهٔ زنده اجرا شود */
    int *data = (int *)malloc((size_t)(n > 0 ? n : 1) * sizeof(int));

    if (data == NULL) {
        fprintf(stderr, "خطای تخصیص حافظه\n");
        return -1;
    }

    if (n <= 0) {
    /* باگ عمدی: free(data) قبل از return فراموش شده است */
    return -2;
    }

    int sum = 0;
    for (int i = 0; i < n; i++) {
        data[i] = i;
        sum += data[i];
    }

    free(data);
    return sum;
}

int main(void) {
    /* فراخوانی با n = -5 تا مسیر خطا اجرا شود */
    int result = process(-5);
    printf("نتیجه: %d\n", result);
    return 0;
}
