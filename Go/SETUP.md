# راه‌اندازی محیط Go

## ۱. نصب
از صفحهٔ رسمی دانلود کنید: https://go.dev/dl/

- ویندوز: نصب‌کنندهٔ MSI
- macOS: pkg یا Homebrew (`brew install go`)
- لینوکس: آرشیو رسمی یا بستهٔ توزیع

## ۲. تأیید
ترمینال جدید باز کنید:

```bash
go version
go env GOVERSION GOROOT GOPATH
```

باید نسخهٔ **1.22 یا جدیدتر** ببینید.

## ۳. ویرایشگر
- VS Code: افزونهٔ رسمی **Go** را نصب کنید و در اولین بار ابزارها (`gopls`, `dlv`) را تأیید کنید.
- اختیاری: `go install golang.org/x/tools/cmd/goimports@latest`

## ۴. کار با این دوره
از ریشهٔ `Go/`:

```bash
cd session-01-intro/examples
go run .
```

فایل `Go/go.mod` ماژول آموزشی دوره است؛ برای هر جلسه معمولاً همان ماژول ریشه کافی است یا داخل examples با `go run .` اجرا می‌کنید.

## ۵. عیب‌یابی سریع
| مشکل | کار |
|------|-----|
| `go` پیدا نمی‌شود | PATH و باز کردن مجدد ترمینال |
| اجرای تست fail | داخل همان پوشهٔ examples باشید |
| خطای encoding در خروجی فارسی ویندوز | فونت ترمینال UTF-8؛ در PowerShell گاهی `[Console]::OutputEncoding` |
