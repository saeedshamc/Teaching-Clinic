/*
 * تمرین ۱ — خواندن نام با fgets و شمارش طول با حلقه
 * TODO: طول رشته را با حلقه بشمارید (بدون استفاده از strlen).
 */

#include <stdio.h>

int main(void) {
    char name[50];

    printf("نام: ");
    if (fgets(name, sizeof(name), stdin) == NULL) {
        return 1;
    }

    /* شمارش دستی حروف تا \0 */
    int count = 0;
    while (name[count] != '\0') {
        count++;
    }
    /* fgets ممکن است \n را نگه داشته باشد؛ اگر بود از شمارش کم می‌کنیم */
    if (count > 0 && name[count - 1] == '\n') {
        count--;
    }

    printf("طول نام: %d\n", count);
    return 0;
}
