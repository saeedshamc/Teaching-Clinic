#!/usr/bin/env bash

# تمرین ۲: ساخت Image
# هدف: یک اسکریپت بنویسید که:
# 1. یک Dockerfile ایجاد کند
# 2. image را بسازد
# 3. container را اجرا کند
# 4. تست انجام دهد

# راه‌حل:
echo "=== ساخت Docker Image ==="

# بررسی Docker
if ! docker info &>/dev/null; then
    echo "خطا: Docker در حال اجرا نیست"
    exit 1
fi

# ایجاد دایرکتوری
BUILD_DIR="/tmp/docker-build-$$"
mkdir -p "$BUILD_DIR"
cd "$BUILD_DIR"

# 1. ایجاد Dockerfile
echo "1. ایجاد Dockerfile:"
cat > Dockerfile << 'EOF'
FROM alpine:latest
RUN apk add --no-cache nginx
RUN echo "Hello from custom Docker image" > /usr/share/nginx/html/index.html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
EOF

cat Dockerfile

# 2. ساخت image
echo -e "\n2. ساخت Image:"
IMAGE_NAME="my-custom-nginx-$$"
docker build -t "$IMAGE_NAME" .

if [ $? -eq 0 ]; then
    echo "✓ Image ساخته شد: $IMAGE_NAME"
else
    echo "✗ خطا در ساخت image"
    exit 1
fi

# 3. اجرا container
echo -e "\n3. اجرا Container:"
CONTAINER_NAME="my-nginx-container-$$"
docker run -d -p 8081:80 --name "$CONTAINER_NAME" "$IMAGE_NAME"

if [ $? -eq 0 ]; then
    echo "✓ Container اجرا شد: $CONTAINER_NAME"
else
    echo "✗ خطا در اجرای container"
    exit 1
fi

# 4. تست
echo -e "\n4. تست Container:"
sleep 3
if curl -s localhost:8081 | grep -q "Hello from custom Docker image"; then
    echo "✓ تست موفق: محتوا صحیح است"
else
    echo "✗ تست ناموفق: محتوا صحیح نیست"
fi

# 5. Cleanup
echo -e "\n5. Cleanup:"
docker stop "$CONTAINER_NAME"
docker rm "$CONTAINER_NAME"
docker rmi "$IMAGE_NAME"

cd /tmp
rm -rf "$BUILD_DIR"

echo "✓ Cleanup کامل شد"
