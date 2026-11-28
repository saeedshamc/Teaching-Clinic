# پاسخ تمرین‌های جلسه ۳

## آسان
```bash
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
```

## متوسط
```bash
cd my_app
git init
git add .
git commit -m "chore: initial commit"
git branch -M main
git remote add origin git@github.com:user/repo.git
git push -u origin main

git checkout -b feature-login
echo "test" > test.txt
git add test.txt && git commit -m "test"
git checkout main && git merge feature-login
```

## سخت
```bash
ssh-keygen -t ed25519 -C "you@example.com"
cat ~/.ssh/id_ed25519.pub   # → GitHub Settings → SSH keys
git remote set-url origin git@github.com:user/repo.git

git checkout feature
git rebase main             # تغییرات feature را روی main بازچین
```
