# جلسه ۲۴: مبانی Docker (Docker Basics)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه Docker
- نصب و پیکربندی Docker
- Docker Images و Containers
- Dockerfile
- Docker Compose
- مدیریت Docker با Bash
- مثال‌های عملی

## مفاهیم پایه Docker

Docker یک پلتفرم containerization است که اجازه می‌دهد برنامه‌ها را در محیط‌های ایزوله اجرا کنید.

- **Image** - الگوی immutable برای ایجاد container
- **Container** - نمونه در حال اجرا از image
- **Dockerfile** - اسکریپت برای ساخت image
- **Docker Compose** - مدیریت چند container

## نصب Docker

```bash
# Ubuntu/Debian
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh

# macOS
brew install docker
```

## مثال ۱: نصب و بررسی Docker

فایل [examples/01-docker-install.sh](examples/01-docker-install.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== نصب و بررسی Docker ==="

# بررسی نصب Docker
if command -v docker &>/dev/null; then
    echo "Docker نصب است"
    docker --version
else
    echo "Docker نصب نیست"
    echo "نصب با:"
    echo "curl -fsSL https://get.docker.com -o get-docker.sh"
    echo "sudo sh get-docker.sh"
fi

# بررسی Docker daemon
echo -e "\nبررسی Docker daemon:"
if docker info &>/dev/null; then
    echo "✓ Docker daemon در حال اجراست"
else
    echo "✗ Docker daemon در حال اجرا نیست"
    echo "راه‌اندازی با: sudo systemctl start docker"
fi

# لیست images
echo -e "\nلیست Docker Images:"
docker images 2>/dev/null || echo "هیچ imageای وجود ندارد"

# لیست containers
echo -e "\nلیست Docker Containers:"
docker ps -a 2>/dev/null || echo "هیچ containerای وجود ندارد"
```

### توضیح خط به خط:

- `docker --version` - نسخه Docker
- `docker info` - اطلاعات Docker daemon
- `docker images` - لیست images
- `docker ps -a` - لیست تمام containers

## Docker Images

```bash
docker pull image        - دانلود image
docker build -t name .   - ساخت image از Dockerfile
docker images            - لیست images
docker rmi image        - حذف image
```

## مثال ۲: مدیریت Images

فایل [examples/02-docker-images.sh](examples/02-docker-images.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== مدیریت Docker Images ==="

# دانلود image
echo -e "\n1. دانلود Ubuntu image:"
docker pull ubuntu:latest

# لیست images
echo -e "\n2. لیست Images:"
docker images

# جستجوی image
echo -e "\n3. جستجوی Image:"
docker search nginx --limit 5

# حذف image
echo -e "\n4. حذف Image:"
echo "docker rmi ubuntu:latest"

# cleanup dangling images
echo -e "\n5. Cleanup Dangling Images:"
docker image prune -f
```

### توضیح خط به خط:

- `docker pull` - دانلود image از Docker Hub
- `docker images` - لیست images محلی
- `docker search` - جستجو در Docker Hub
- `docker rmi` - حذف image
- `docker image prune` - cleanup images استفاده نشده

## Docker Containers

```bash
docker run image        - اجرا container
docker ps               - لیست containers در حال اجرا
docker ps -a            - لیست تمام containers
docker stop container   - توقف container
docker rm container     - حذف container
```

## مثال ۳: مدیریت Containers

فایل [examples/03-docker-containers.sh](examples/03-docker-containers.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== مدیریت Docker Containers ==="

# اجرا container
echo -e "\n1. اجرا Container:"
docker run -d --name test-nginx nginx:latest

# لیست containers در حال اجرا
echo -e "\n2. لیست Containers در حال اجرا:"
docker ps

# لیست تمام containers
echo -e "\n3. لیست تمام Containers:"
docker ps -a

# توقف container
echo -e "\n4. توقف Container:"
docker stop test-nginx

# حذف container
echo -e "\n5. حذف Container:"
docker rm test-nginx
```

### توضیح خط به خط:

- `docker run -d` - اجرا در پس‌زمینه
- `--name` - نام container
- `docker ps` - containers در حال اجرا
- `docker ps -a` - تمام containers
- `docker stop` - توقف container
- `docker rm` - حذف container

## Dockerfile

Dockerfile برای ساخت custom image استفاده می‌شود.

```dockerfile
FROM ubuntu:latest
RUN apt update && apt install -y nginx
COPY index.html /var/www/html/
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```

## مثال ۴: ساخت Dockerfile

فایل [examples/04-dockerfile.sh](examples/04-dockerfile.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `FROM` - base image
- `RUN` - اجرای دستورات
- `EXPOSE` - exposing پورت
- `CMD` - دستور پیش‌فرض
- `docker build` - ساخت image
- `-p 8080:80` - mapping پورت

## Docker Compose

Docker Compose برای مدیریت چند container استفاده می‌شود.

```yaml
version: '3'
services:
  web:
    image: nginx
    ports:
      - "80:80"
  db:
    image: mysql
```

## مثال ۵: Docker Compose

فایل [examples/05-docker-compose.sh](examples/05-docker-compose.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `docker-compose up -d` - اجرا در پس‌زمینه
- `docker-compose ps` - لیست services
- `docker-compose down` - توقف و حذف

## مدیریت Docker با Bash

## مثال ۶: Docker Management Script

فایل [examples/06-docker-management.sh](examples/06-docker-management.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== مدیریت Docker با Bash ==="

# تابع بررسی وضعیت
check_docker_status() {
    echo "وضعیت Docker:"
    if docker info &>/dev/null; then
        echo "✓ Docker در حال اجراست"
        docker info | grep "Server Version"
    else
        echo "✗ Docker در حال اجرا نیست"
    fi
}

# تابع cleanup
docker_cleanup() {
    echo "Cleanup Docker:"
    echo "1. توقف تمام containers:"
    docker stop $(docker ps -aq) 2>/dev/null || echo "هیچ containerای در حال اجرا نیست"
    
    echo "2. حذف تمام containers:"
    docker rm $(docker ps -aq) 2>/dev/null || echo "هیچ containerای وجود ندارد"
    
    echo "3. حذف dangling images:"
    docker image prune -f
    
    echo "4. حذف unused volumes:"
    docker volume prune -f
}

# تابع مانیتورینگ
docker_monitor() {
    echo "مانیتورینگ Docker:"
    echo "1. CPU و Memory:"
    docker stats --no-stream
    
    echo -e "\n2. Disk Usage:"
    docker system df
}

# اجرا
check_docker_status
docker_monitor
```

### توضیح خط به خط:

- `check_docker_status` - بررسی وضعیت Docker
- `docker_cleanup` - cleanup resources
- `docker_monitor` - مانیتورینگ resources
- `docker stats` - CPU/memory usage
- `docker system df` - disk usage

## نکات امنیتی مهم

### ۱. استفاده از non-root user

```bash
# افزودن کاربر به docker group
sudo usermod -aG docker $USER
```

### ۲. اسکن کردن images

```bash
docker scan image-name
```

### ۳. استفاده از official images

```bash
# همیشه از official images استفاده کنید
docker pull nginx:official
```

## مثال ۷: Docker Security

فایل [examples/07-docker-security.sh](examples/07-docker-security.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== امنیت Docker ==="

# 1. اسکن image
echo -e "\n1. اسکن Image:"
echo "docker scan nginx:latest"

# 2. بررسی vulnerabilities
echo -e "\n2. بررسی Vulnerabilities:"
echo "docker image ls --format '{{.Repository}}:{{.Tag}}' | xargs -I {} docker scan {}"

# 3. استفاده از non-root container
echo -e "\n3. اجرا با non-root user:"
echo "docker run --user 1000:1000 nginx:latest"

# 4. محدود کردن resources
echo -e "\n4. محدود کردن Resources:"
echo "docker run --memory=512m --cpus=1 nginx:latest"

# 5. read-only filesystem
echo -e "\n5. Read-only Filesystem:"
echo "docker run --read-only nginx:latest"
```

### توضیح خط به خط:

- `docker scan` - اسکن vulnerabilities
- `--user` - اجرا با non-root
- `--memory` - محدودیت memory
- `--cpus` - محدودیت CPU
- `--read-only` - filesystem read-only

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. اجرا به عنوان root

❌ اشتباه:
```bash
sudo docker run  - خطرناک
```

✅ درست:
```bash
docker run  - با user group
```

### ۲. استفاده از latest tag

❌ اشتباه:
```bash
docker pull nginx:latest  - غیرقابل پیش‌بینی
```

✅ درست:
```bash
docker pull nginx:1.21  - نسخه مشخص
```

### ۳. فراموش کردن cleanup

❌ اشتباه:
```bash
# بدون cleanup
```

✅ درست:
```bash
docker system prune -a  - cleanup منظم
```

## بهترین شیوه‌ها (Best Practices)

1. **از specific tags استفاده کنید** - نه latest
2. **cleanup منظم انجام دهید** - برای جلوگیری از پر شدن دیسک
3. **resources را محدود کنید** - برای جلوگیری از مصرف زیاد
4. **از non-root استفاده کنید** - برای امنیت
5. **images را اسکن کنید** - برای security

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه Docker
- نصب و پیکربندی
- مدیریت Images
- مدیریت Containers
- Dockerfile
- Docker Compose
- مدیریت Docker با Bash
- امنیت Docker
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): مدیریت Docker

یک اسکریپت بنویسید که:
1. وضعیت Docker را بررسی کند
2. لیست containers را نمایش دهد
3. cleanup انجام دهد

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): ساخت Image

یک اسکریپت بنویسید که:
1. یک Dockerfile ایجاد کند
2. image را بسازد
3. container را اجرا کند
4. تست انجام دهد

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، CI/CD Basics را یاد می‌گیریم.
