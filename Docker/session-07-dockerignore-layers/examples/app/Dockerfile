FROM nginx:alpine
# لایهٔ پایدارتر اول
COPY static/index.html /usr/share/nginx/html/index.html
# فایل‌های فرعی دیرتر — تغییرشان کل وابستگی را bust نمی‌کند
COPY static/note.txt /usr/share/nginx/html/note.txt
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
