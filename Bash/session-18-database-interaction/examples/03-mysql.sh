#!/usr/bin/env bash

# توجه: برای اجرای این اسکریپت، MySQL باید نصب باشد

echo "مثال MySQL:"
echo "mysql -u username -p database -e 'SELECT * FROM table;'"

# درج داده
echo "mysql -u username -p database -e \"INSERT INTO table (col1, col2) VALUES ('val1', 'val2');\""

# خواندن داده
echo "mysql -u username -p database -e 'SELECT * FROM table;'"
