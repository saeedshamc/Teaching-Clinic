# جلسه ۰۳ — Git و GitHub

## اهداف یادگیری
- Git را نصب و تنظیم کنید (`user.name`/`user.email`)
- ریپازیتوری بسازید، commit و push کنید
- به GitHub با SSH یا Token متصل شوید
- `.gitignore` برای پروژهٔ Java بنویسید

## پیش‌نیازها
- جلسات ۰۱ و ۰۲ کامل شده

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### نصب Git
- ویندوز: git-scm.com → installer → گزینه‌های پیش‌فرض کافی است.
- لینوکس: `sudo apt install git`
- macOS: `brew install git` یا Xcode CLT

### تنظیم هویت (یک‌بار)
```bash
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
git config --global init.defaultBranch main
```
بدون این‌ها commit با خطا می‌خورد.

### چرخهٔی
```bash
git init                    # ساخت ریپو محلی
git status                  # وضعیت فایل‌ها
git add .                   # stage کردن
git commit -m "message"     # commit
git log --oneline           # تاریخچه
git push origin main        # push به ریموت
```

### GitHub
1. github.com → New repository → نام `java-course` → **Private/Public** → بدون README (چون لوکال داریم).
2. اتصال SSH (توصیه): در GitHub → Settings → SSH keys → کلید عمومی `~/.ssh/id_ed25519.pub` را اضافه کنید. تست: `ssh -T git@github.com`.
3. یا HTTPS + Personal Access Token (رمز عبور معمولی کار نمی‌کند).

```bash
git remote add origin git@github.com:USER/java-course.git
# یا: git remote add origin https://github.com/USER/java-course.git
git push -u origin main
```

### .gitignore برای Java
```
# build outputs
out/
target/
build/
*.class
*.jar

# IDE
.idea/
*.iml
.classpath
.project
.settings/
.vscode/

# OS
.DS_Store
Thumbs.db
```

## کد مهم

`src/GitDemo.java` — یک برنامهٔ ساده که در ریپو commit می‌شود تا چرخهٔ git روی کد واقعی تمرین شود.

## خطاهای رایج
- `fatal: not a git repository` → داخل پوشهٔ پروژه نیستید یا `git init` نکردید.
- `Permission denied (publickey)` → SSH key اضافه نشده یا `ssh -T git@github.com` تست نشده.
- `remote: Repository not found` → نام ریپو/یوزر اشتباه یا دسترسی ندارد.
- فایل‌های `.class` کامیت شده → `.gitignore` را اضافه و `git rm --cached` کنید.

## جمع‌بندی
- Git = version control محلی؛ GitHub = هاست ریموت.
- `.gitignore` برای Java: `out/`, `target/`, `*.class`, `.idea/`.
- هر جلسه را در ریپو خود commit کنید تا عادت شود.

**فاز ۱ ادامه دارد — جلسهٔ بعد: syntax و متغیرها در Java.**
