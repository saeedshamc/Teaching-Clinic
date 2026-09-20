# راه‌اندازی محیط Docker

نسخهٔ هدف: **Docker Engine / Docker Desktop** با CLI مدرن (`docker compose` به‌صورت plugin).

## ۱. قانون طلایی این دوره
تمرین‌ها فقط روی **ماشین محلی شما** و پوشه‌های داخل `Docker/` اجرا می‌شوند.

هرگز:
- اپ‌های واقعی Teaching Clinic را برای آزمایش Docker بازنویسی نکنید
- `docker system prune -a --volumes` را روی ماشین کاری بدون فکر اجرا نکنید
- secret واقعی (پسورد production، token) را داخل image یا این ریپو نگذارید

## ۲. نصب

### ویندوز
1. [Docker Desktop for Windows](https://docs.docker.com/desktop/setup/install/windows-install/) را نصب کنید.
2. در صورت نیاز **WSL2** را فعال کنید (Desktop معمولاً راهنمایی می‌کند).
3. Docker Desktop را باز کنید تا daemon بالا بیاید.

تأیید در PowerShell جدید:

```powershell
docker version
docker compose version
```

### macOS
```bash
# از سایت Docker Desktop، یا:
brew install --cask docker
docker version
docker compose version
```

### لینوکس
```bash
# روش رسمی توزیع خودتان یا get.docker.com
sudo apt install docker.io docker-compose-v2   # نمونه Debian/Ubuntu
sudo usermod -aG docker $USER   # سپس logout/login
docker version
docker compose version
```

روی لینوکس معمولاً Engine کافی است؛ Desktop الزامی نیست.

## ۳. تفاوت `docker compose` و `docker-compose`
این دوره از **`docker compose`** (فاصله، plugin رسمی) استفاده می‌کند.

اگر فقط `docker-compose` (خط تیره) دارید، همان دستورات را با خط تیره امتحان کنید؛ ولی هدف نصب plugin مدرن است.

## ۴. بررسی سلامت daemon

```powershell
docker info
docker run --rm hello-world
```

اگر `Cannot connect to the Docker daemon` دیدید: Desktop/سرویس را روشن کنید.

## ۵. اجرای مثال‌ها
از ریشهٔ ریپو یا داخل `Docker/`:

```powershell
powershell -File Docker/session-01-intro/examples/01-check-docker.ps1
```

برای مثال‌های Dockerfile:

```powershell
cd Docker/session-06-dockerfile/examples/app
docker build -t clinic-s06 .
docker run --rm -p 8080:80 clinic-s06
```

## ۶. عیب‌یابی
| مشکل | کار |
|------|-----|
| `docker` پیدا نمی‌شود | PATH؛ ترمینال جدید بعد از نصب Desktop |
| daemon متصل نیست | Docker Desktop را Start کنید |
| پورت در حال استفاده | پورت دیگری با `-p 8081:80` |
| permission denied (لینوکس) | کاربر در گروه `docker`؛ یا موقت `sudo` |
| کندی روی ویندوز | مطمئن شوید backend روی WSL2 است |
