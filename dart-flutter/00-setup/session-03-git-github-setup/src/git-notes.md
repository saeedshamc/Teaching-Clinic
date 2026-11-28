# دستورات Git پرکاربرد — جلسه ۳

## پایه
```bash
git init                    # ساخت repo
git status                  # وضعیت
git add .                   # stage همه
git add file.dart           # stage فایل خاص
git commit -m "message"     # commit
git log --oneline           # تاریخچه
```

## branch
```bash
git branch feature-x        # ساخت branch
git checkout feature-x      # رفتن به branch
git merge feature-x         # merge به main
git branch -d feature-x     # حذف branch
```

## remote
```bash
git remote -v               # لیست remote
git remote add origin URL   # اضافه کردن
git fetch                   # دانلود بدون merge
git pull                    # fetch + merge
git push -u origin main     # push + تنظیم upstream
```

## undo
```bash
git checkout -- file        # برگرداندن تغییرات unstaged
git reset --soft HEAD~1     # undo آخرین commit (تغییرات بماند)
git reset --hard HEAD~1     # undo کامل
```

## ignore
```bash
echo "build/" >> .gitignore
git rm --cached build/      # حذف از index بدون حذف فایل
```
