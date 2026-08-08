/*
 * تمرین ۲ — Echo Server
 *
 * سرور هر پیامی که از کلاینت می‌گیرد را همان‌طور برمی‌گرداند و تا
 * دریافت «quit» سرویس می‌دهد.
 *
 * کامپایل و اجرا (لینوکس/WSL):
 *   gcc -Wall -Wextra -o exercise_02 exercise_02.c
 *   ./exercise_02
 *
 * سپس با 02_tcp_client یا exercise_01 به آن وصل شوید.
 */

#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>   /* ssize_t */
#include <unistd.h>

#define PORT 8080

int main(void) {
    int server_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_fd < 0) {
        perror("socket");
        return 1;
    }

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

    if (listen(server_fd, 5) < 0) {
        perror("listen");
        close(server_fd);
        return 1;
    }
    printf("Echo server روی پورت %d در انتظار است (برای خروج Ctrl+C)...\n", PORT);

    /* چند کلاینت پشت سر هم */
    while (1) {
        int client_fd = accept(server_fd, NULL, NULL);
        if (client_fd < 0) {
            perror("accept");
            continue;
        }
        printf("کلاینت متصل شد\n");

        char buffer[256];
        ssize_t n;

        /* تا وقتی پیام «quit» نیامده، پیام‌ها را برمی‌گردانیم */
        while ((n = recv(client_fd, buffer, sizeof(buffer) - 1, 0)) > 0) {
            buffer[n] = '\0';

            if (strcmp(buffer, "quit") == 0) {
                printf("کلاینت خداحافظی کرد\n");
                break;
            }

            printf("دریافت: %s\n", buffer);
            send(client_fd, buffer, (int)n, 0);   /* echo */
        }

        close(client_fd);
    }

    close(server_fd);
    return 0;
}
