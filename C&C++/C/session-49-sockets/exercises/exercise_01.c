/*
 * تمرین ۱ — کلاینت با ورودی کاربر
 *
 * کلاینت پیامی را با scanf از کاربر می‌گیرد و به سرور می‌فرستد.
 * قبل از اجرا، سرور (01_tcp_server) باید روشن باشد.
 *
 * کامپایل (لینوکس/WSL):
 *   gcc -Wall -Wextra -o exercise_01 exercise_01.c
 */

#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>

#define PORT 8080

int main(void) {
    int sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock < 0) {
        perror("socket");
        return 1;
    }

    struct sockaddr_in server;
    memset(&server, 0, sizeof(server));
    server.sin_family = AF_INET;
    server.sin_port = htons(PORT);
    if (inet_pton(AF_INET, "127.0.0.1", &server.sin_addr) <= 0) {
        perror("inet_pton");
        close(sock);
        return 1;
    }

    if (connect(sock, (struct sockaddr *)&server, sizeof(server)) < 0) {
        perror("connect");
        close(sock);
        return 1;
    }

    /* گرفتن پیام از کاربر */
    char message[256];
    printf("پیام خود را بنویسید: ");
    fgets(message, sizeof(message), stdin);

    /* حذف newline از انتهای پیام */
    size_t len = strlen(message);
    if (len > 0 && message[len - 1] == '\n') {
        message[len - 1] = '\0';
    }

    send(sock, message, (int)strlen(message), 0);

    char buffer[256] = {0};
    recv(sock, buffer, sizeof(buffer) - 1, 0);
    printf("پاسخ سرور: %s\n", buffer);

    close(sock);
    return 0;
}
