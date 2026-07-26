#!/usr/bin/env bash

# سورس کردن فایل کتابخانه
source examples/mylib.sh
# یا: . examples/mylib.sh

# استفاده از توابع کتابخانه
echo "جمع 5 و 3: $(add 5 3)"
echo "تفریق 10 و 4: $(subtract 10 4)"
echo "ضرب 6 و 7: $(multiply 6 7)"

greet "علی"
