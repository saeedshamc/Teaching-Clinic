#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/test.txt << EOF
a
aa
aaa
aaaa
ab
aab
aaab
EOF

echo "محتوای فایل:"
cat /tmp/test.txt

# ستاره (*) - صفر یا بیشتر
echo -e "\nجستجوی 'a*':"
grep "a*" /tmp/test.txt

# پلاس (+) - یک یا بیشتر
echo -e "\nجستجوی 'a+':"
grep -E "a+" /tmp/test.txt

# علامت سوال (?) - صفر یا یک
echo -e "\nجستجوی 'a?b':"
grep -E "a?b" /tmp/test.txt

# تعداد دقیق {n}
echo -e "\nجستجوی 'a{3}':"
grep -E "a{3}" /tmp/test.txt

# محدوده {n,m}
echo -e "\nجستجوی 'a{2,3}':"
grep -E "a{2,3}" /tmp/test.txt

# پاکسازی
rm /tmp/test.txt
