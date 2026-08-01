/*
 * تمرین ۱ — تابع swap اشتباه (pass by value)
 * TODO: اجرا کنید و ببینید چرا a و b در main عوض نمی‌شوند.
 * سپس در یک کامنت دلیل را توضیح دهید.
 */

#include <stdio.h>

void swap(int x, int y) {
    int temp = x;
    x = y;
    y = temp;
    /* این تغییرات فقط روی کپی‌ها اعمال می‌شود */
}

int main(void) {
    int a = 10, b = 20;
    printf("قبل: a=%d, b=%d\n", a, b);
    swap(a, b);
    printf("بعد: a=%d, b=%d (تغییری نکرد!)\n", a, b);
    return 0;
}
