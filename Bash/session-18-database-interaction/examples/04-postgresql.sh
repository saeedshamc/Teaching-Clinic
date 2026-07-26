#!/usr/bin/env bash

# توجه: برای اجرای این اسکریپت، PostgreSQL باید نصب باشد

echo "مثال PostgreSQL:"
echo "psql -U username -d database -c 'SELECT * FROM table;'"

# درج داده
echo "psql -U username -d database -c \"INSERT INTO table (col1, col2) VALUES ('val1', 'val2');\""

# خواندن داده
echo "psql -U username -d database -c 'SELECT * FROM table;'"
