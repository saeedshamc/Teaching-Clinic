/*
 * جلسه ۴۹ — سرور TCP ساده
 *
 * کامپایل و اجرا (لینوکس/WSL):
 *   gcc -Wall -Wextra -o 01_tcp_server 01_tcp_server.c
 *   ./01_tcp_server
 *
 * سپس در ترمینال دیگر کلاینت را اجرا کنید (02_tcp_client.c).
 */

#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>

#define PORT 8080

int main(void) {
    /* ۱) ساخت سوکت TCP */
    int server_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_fd < 0) {
        perror("socket");
        return 1;
    }

    /* ۲) اتصال به پورت 8080 روی همهٔ رابط‌ها */
    struct sockaddr_in addr;
    memset(&addr, 0, sizeof(addr));
    addr.sin_family = AF_INET;
    addr.sin_addr.s_addr = htonl(INADDR_ANY);
    addr.sin_port = htons(PORT);

    if (bind(server_fd, (struct sockaddr *)&addr, sizeof(addr)) < 0) {
        perror("bind");
        close(server_fd);
        return 1;
    }

    /* ۳) گوش دادن برای اتصال‌های ورودی */
    if (listen(server_fd, 5) < 0) {
        perror("listen");
        close(server_fd);
        return 1;
    }
    printf("سرور روی پورت %d در انتظار اتصال است...\n", PORT);

    /* ۴) پذیرش یک کلاینت */
    int client_fd = accept(server_fd, NULL, NULL);
    if (client_fd < 0) {
        perror("accept");
        close(server_fd);
        return 1;
    }
    printf("کلاینت متصل شد\n");

    /* ۵) دریافت پیام و پاسخ */
    char buffer[256] = {0};
    recv(client_fd, buffer, sizeof(buffer) - 1, 0);
    printf("دریافت شد: %s\n", buffer);

    const char *reply = "سلام! پیامت رسید.";
    send(client_fd, reply, (int)strlen(reply), 0);

    /* ۶) بستن سوکت‌ها */
    close(client_fd);
    close(server_fd);
    return 0;
}
