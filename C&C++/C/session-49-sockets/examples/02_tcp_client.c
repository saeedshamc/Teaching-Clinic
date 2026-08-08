/*
 * جلسه ۴۹ — کلاینت TCP ساده
 *
 * کامپایل (لینوکس/WSL):
 *   gcc -Wall -Wextra -o 02_tcp_client 02_tcp_client.c
 *
 * قبل از اجرا، سرور (01_tcp_server) باید روشن باشد:
 *   ./02_tcp_client
 */

#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>

#define PORT 8080

int main(void) {
    /* ۱) ساخت سوکت */
    int sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock < 0) {
        perror("socket");
        return 1;
    }

    /* ۲) آدرس سرور: 127.0.0.1 پورت 8080 */
    struct sockaddr_in server;
    memset(&server, 0, sizeof(server));
    server.sin_family = AF_INET;
    server.sin_port = htons(PORT);
    if (inet_pton(AF_INET, "127.0.0.1", &server.sin_addr) <= 0) {
        perror("inet_pton");
        close(sock);
        return 1;
    }

    /* ۳) اتصال به سرور */
    if (connect(sock, (struct sockaddr *)&server, sizeof(server)) < 0) {
        perror("connect");
        close(sock);
        return 1;
    }
    printf("به سرور متصل شد\n");

    /* ۴) ارسال پیام و دریافت پاسخ */
    const char *message = "سلام سرور!";
    send(sock, message, (int)strlen(message), 0);

    char buffer[256] = {0};
    recv(sock, buffer, sizeof(buffer) - 1, 0);
    printf("پاسخ سرور: %s\n", buffer);

    /* ۵) بستن سوکت */
    close(sock);
    return 0;
}
