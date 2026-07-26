#!/usr/bin/env bash

# Here Document
echo "ایجاد فایل با Here Document:"
cat > /tmp/heredoc.txt << EOF
این یک فایل است
با چند خط
ایجاد شده با Here Document
EOF

cat /tmp/heredoc.txt

# Here String
echo -e "\nاستفاده از Here String:"
grep "فایل" <<< "این یک فایل است برای تست"

# Here Document با متغیر
NAME="علی"
cat << EOF
سلام $NAME
این یک Here Document با متغیر است
EOF

# Here Document بدون تفسیر (quoted)
cat << 'EOF'
این $NAME تفسیر نمی‌شود
EOF

# پاکسازی
rm /tmp/heredoc.txt
