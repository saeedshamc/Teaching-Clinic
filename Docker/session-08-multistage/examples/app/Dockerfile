# syntax=docker/dockerfile:1
FROM alpine:3.20 AS builder
WORKDIR /src
RUN echo '<!DOCTYPE html><html lang="fa" dir="rtl"><head><meta charset="utf-8"><title>S08</title></head><body><h1>ساخته‌شده در stage builder</h1></body></html>' > /src/index.html

FROM nginx:alpine
COPY --from=builder /src/index.html /usr/share/nginx/html/index.html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
