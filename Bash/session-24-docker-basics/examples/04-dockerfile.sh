#!/usr/bin/env bash

echo "=== ساخت Dockerfile ==="

# ایجاد دایرکتوری
mkdir -p /tmp/docker-test
cd /tmp/docker-test

# ایجاد Dockerfile
cat > Dockerfile << 'EOF'
FROM ubuntu:latest
RUN apt update && apt install -y nginx
RUN echo "Hello from Docker" > /var/www/html/index.html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
EOF

echo "Dockerfile ایجاد شد"
cat Dockerfile

# ساخت image
echo -e "\nساخت Image:"
docker build -t my-nginx .

# اجرا container
echo -e "\nاجرای Container:"
docker run -d -p 8080:80 --name my-nginx-container my-nginx

# تست
echo -e "\nتست:"
sleep 2
curl localhost:8080 2>/dev/null || echo "نیاز به صبر بیشتر یا container در حال اجرا نیست"

# cleanup
echo -e "\nCleanup:"
docker stop my-nginx-container
docker rm my-nginx-container
docker rmi my-nginx

cd /tmp
rm -rf /tmp/docker-test
