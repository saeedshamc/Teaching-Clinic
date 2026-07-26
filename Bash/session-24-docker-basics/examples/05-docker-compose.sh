#!/usr/bin/env bash

echo "=== Docker Compose ==="

# بررسی نصب docker-compose
if command -v docker-compose &>/dev/null; then
    echo "docker-compose نصب است"
    docker-compose --version
else
    echo "docker-compose نصب نیست"
    echo "نصب با: sudo apt install docker-compose"
fi

# ایجاد docker-compose.yml
mkdir -p /tmp/compose-test
cd /tmp/compose-test

cat > docker-compose.yml << 'EOF'
version: '3'
services:
  web:
    image: nginx:latest
    ports:
      - "8080:80"
    container_name: my-web
EOF

echo "docker-compose.yml ایجاد شد"
cat docker-compose.yml

# اجرا
echo -e "\nاجرای Docker Compose:"
docker-compose up -d

# لیست services
echo -e "\nلیست Services:"
docker-compose ps

# توقف
echo -e "\nتوقف:"
docker-compose down

cd /tmp
rm -rf /tmp/compose-test
