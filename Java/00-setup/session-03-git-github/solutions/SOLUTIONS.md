# پاسخ تمرین‌های جلسه ۰۳

## آسان

**۱.**
```bash
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
git config --list | grep user
```

**۲.**
```bash
mkdir testrepo && cd testrepo
git init
echo "test" > file.txt
git add . && git commit -m "first commit"
```

## متوسط

**۳.** در GitHub → New repo → نام `java-course` → Local: `git remote add origin git@github.com:USER/java-course.git` → `git push -u origin main`. اگر SSH ندارید: `git remote add origin https://github.com/USER/java-course.git` و با Token authenticate کنید.

**۴.** `.gitignore` را با محتوای README بسازید؛ برای نادیده گرفتن فایل class: `*.class` در gitignore + `git rm --cached file.class`.

## سخت

**۵.**
```bash
git checkout -b feature/x
echo "change" >> file.txt
git add . && git commit -m "feature x"
git checkout main
git merge feature/x
git push origin main
```

**۶.** `git log --oneline --graph --all` تاریخچه را با شاخه‌ها نشان می‌دهد؛ `git log -p` diff هر commit را می‌دهد.
