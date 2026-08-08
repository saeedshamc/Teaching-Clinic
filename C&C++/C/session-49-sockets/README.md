# جلسهٔ ۴۹ — مقدمه‌ای بر سوکت‌ها (Sockets)

## اهداف یادگیری

در پایان این جلسه می‌توانید:

- مفهوم سوکت و ارتباط TCP را درک کنید
- یک سرور ساده با `socket/bind/listen/accept` بسازید
- یک کلاینت با `socket/connect` بنویسید و با سرور ارتباط برقرار کنید
- داده را با `send/recv` تبادل کنید

---

## سوکت چیست؟

**سوکت** (Socket) نقطهٔ پایانی یک ارتباط شبکه‌ای است — مثل درگاهی که برنامه از آن به شبکه متصل می‌شود. برنامه‌های مختلف (روی یک ماشین یا ماشین‌های مختلف) از طریق سوکت با هم حرف می‌زنند.

در این جلسه با **TCP** کار می‌کنیم: پروتکل اتصال‌گرا و مطمئن (داده به ترتیب و بدون از دست رفتن می‌رسد) — همان پروتکلی که HTTP روی آن است.

## جریان کلی (TCP)

**سمت سرور:**

```
socket()  ->  bind()  ->  listen()  ->  accept()  ->  recv()/send()  ->  close()
```

**سمت کلاینت:**

```
socket()  ->  connect()  ->  send()/recv()  ->  close()
```

## ساخت سرور

```c
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>

int main(void) {
    /* ۱) ساخت سوکت */
    int server_fd = socket(AF_INET, SOCK_STREAM, 0);

    /* ۲) اتصال به آدرس و پورت 8080 */
    struct sockaddr_in addr;
    memset(&addr, 0, sizeof(addr));
    addr.sin_family = AF_INET;
    addr.sin_addr.s_addr = htonl(INADDR_ANY);
    addr.sin_port = htons(8080);

    bind(server_fd, (struct sockaddr *)&addr, sizeof(addr));
    listen(server_fd, 5);

    /* ۳) پذیرش یک کلاینت */
    int client_fd = accept(server_fd, NULL, NULL);

    /* ۴) دریافت و ارسال */
    char buffer[256];
    recv(client_fd, buffer, sizeof(buffer), 0);
    send(client_fd, "سلام از سرور!", 14, 0);

    close(client_fd);
    close(server_fd);
    return 0;
}
```

نکته‌ها:
- `AF_INET` = خانوادهٔ آدرس IPv4.
- `SOCK_STREAM` = TCP (در مقابل `SOCK_DGRAM` برای UDP).
- `htons/htonl` آدرس‌ها را به ترتیب بایت شبکه (big-endian) تبدیل می‌کنند.
- پورت‌های کوچک‌تر از ۱۰۲۴ نیاز به دسترسی root دارند؛ از 8080 استفاده می‌کنیم.

## ساخت کلاینت

```c
int main(void) {
    int sock = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in server;
    memset(&server, 0, sizeof(server));
    server.sin_family = AF_INET;
    server.sin_port = htons(8080);
    inet_pton(AF_INET, "127.0.0.1", &server.sin_addr);   /* localhost */

    connect(sock, (struct sockaddr *)&server, sizeof(server));

    send(sock, "سلام!", 6, 0);
    char buffer[256];
    recv(sock, buffer, sizeof(buffer), 0);

    close(sock);
    return 0;
}
```

## اجرای آزمایشی

در دو ترمینال (لینوکس/WSL):

```bash
# ترمینال ۱ (سرور)
gcc -Wall -Wextra -o server 01_tcp_server.c
./server

# ترمینال ۲ (کلاینت)
gcc -Wall -Wextra -o client 02_tcp_client.c
./client
```

## اشتباهات رایج

1. **فراموش کردن `htons/htonl`** — پورت/آدرس اشتباه می‌شود.
2. **بستن سوکت بعد از استفاده** — فراموش کردن `close` → خالی شدن توصیفگر فایل (fd leak).
3. **عدم بررسی مقادیر بازگشتی** — `socket/bind/accept` در شکست `-1` برمی‌گردانند.
4. **اجرای سرور قبل از کلاینت** — اگر سرور نباشد `connect` با خطا مواجه می‌شود.
5. **اندازهٔ بافر** — `recv` بیش از سایز بافر نمی‌خواند؛ همیشه سایز را درست بدهید.

## بهترین شیوه‌ها

- مقدار بازگشتی همهٔ فراخوانی‌ها را بررسی کنید (`if (fd < 0) perror(...)`) — مثل جلسهٔ ۳۱.
- در سرور، یک حلقه بگذارید تا چند کلاینت پشت سر هم سرویس شوند.
- `close` را برای هر توصیفگر باز انجام دهید.
- برای تست محلی از `127.0.0.1` استفاده کنید.

## خلاصهٔ جلسه

- سوکت = نقطهٔ پایانی ارتباط شبکه.
- سرور: socket → bind → listen → accept.
- کلاینت: socket → connect.
- `send/recv` داده را تبادل می‌کنند؛ همه‌چیز را بررسی و close کنید.

## تمرین‌ها

### تمرین ۱ (ساده)
کلاینت را طوری تغییر دهید که یک پیام از کاربر (scanf) بگیرد و به سرور بفرستد.

### تمرین ۲ (متوسط)
سرور را به یک «echo server» تبدیل کنید: هر پیامی که از کلاینت می‌گیرد، همان را برمی‌گرداند و تا زمان دریافت «quit» به کلاینت‌ها سرویس می‌دهد.

راه‌حل‌ها در `exercises/` هستند.

## فایل‌های این جلسه

- `examples/01_tcp_server.c` — سرور ساده
- `examples/02_tcp_client.c` — کلاینت ساده
- `exercises/exercise_01.c` — کلاینت با ورودی کاربر
- `exercises/exercise_02.c` — echo server
