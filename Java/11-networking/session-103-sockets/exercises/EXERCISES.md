# تمرین‌های جلسه ۱۰۳ — Sockets

## آسان
1. `ServerSocket` با port 8080 بسازید.
2. `Socket` برای connect به server استفاده کنید.

## متوسط
3. `InputStream`/`OutputStream` برای send/receive استفاده کنید.
4. `new Thread()` برای concurrent clients استفاده کنید.

## سخت
5. `echo` loop با `while ((bytesRead = in.read(buffer)) != -1)` پیاده کنید.
6. `socket.getInetAddress()` برای client info استفاده کنید.
