#!/usr/bin/env bash

# ایجاد فایل HTML نمونه
cat > /tmp/test.html << EOF
<html>
<body>
    <div class="container">
        <p id="p1">پاراگراف ۱</p>
        <p id="p2">پاراگراف ۲</p>
    </div>
</body>
</html>
EOF

echo "فایل HTML:"
cat /tmp/test.html

# استخراج با CSS selector (اگر html-xml-utils نصب باشد)
if command -v hxselect &>/dev/null; then
    echo -e "\nاستخراج با hxselect:"
    hxselect 'p' /tmp/test.html
else
    echo -e "\nhxselect نصب نیست"
    echo "نصب با: sudo apt install html-xml-utils"
fi

# پاکسازی
rm /tmp/test.html
