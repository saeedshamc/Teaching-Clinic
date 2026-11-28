# مراحل push به GitHub — پاسخ جلسه ۳

```bash
# ۱. تنظیم هویت
git config --global user.name "Your Name"
git config --global user.email "you@example.com"

# ۲. در پروژهٔ Flutter
cd my_app
git init

# ۳. .gitignore کپی کنید (از README جلسه)

# ۴. stage و commit
git add .
git commit -m "chore: initial flutter project"

# ۵. branch و remote
git branch -M main
git remote add origin git@github.com:USERNAME/REPO.git

# ۶. push
git push -u origin main
```

## بررسی
- GitHub repo باید `.gitignore` را نشان دهد (نه build/)
- `git status` باید clean باشد
