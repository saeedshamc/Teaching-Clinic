# جلسه ۱۸: تعامل با دیتابیس (Database Interaction)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- اتصال به SQLite از Bash
- اجرای دستورات SQL
- خواندن و نوشتن در دیتابیس
- استفاده از MySQL/MariaDB
- استفاده از PostgreSQL
- مثال‌های عملی

## SQLite در Bash

SQLite یک دیتابیس سبک و بدون سرور است که در Bash استفاده می‌شود.

```bash
sqlite3 database.db "SQL_QUERY"
```

## مثال ۱: SQLite پایه

فایل [examples/01-sqlite-basic.sh](examples/01-sqlite-basic.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

DB_FILE="/tmp/test.db"

# ایجاد دیتابیس و جدول
sqlite3 "$DB_FILE" << EOF
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY,
    name TEXT,
    age INTEGER
);
EOF

echo "دیتابیس و جدول ایجاد شد"

# درج داده
sqlite3 "$DB_FILE" "INSERT INTO users (name, age) VALUES ('علی', 25);"
sqlite3 "$DB_FILE" "INSERT INTO users (name, age) VALUES ('رضا', 30);"
sqlite3 "$DB_FILE" "INSERT INTO users (name, age) VALUES ('محمد', 22);"

echo "داده‌ها درج شد"

# خواندن داده
echo -e "\nمحتوای جدول:"
sqlite3 "$DB_FILE" "SELECT * FROM users;"

# پاکسازی
rm "$DB_FILE"
```

### توضیح خط به خط:

- `sqlite3 "$DB_FILE"` - اتصال به دیتابیس
- `<< EOF` - here document برای دستورات SQL
- `CREATE TABLE` - ایجاد جدول
- `INSERT INTO` - درج داده
- `SELECT * FROM` - خواندن تمام داده‌ها

## مثال ۲: SQLite پیشرفته

فایل [examples/02-sqlite-advanced.sh](examples/02-sqlite-advanced.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

DB_FILE="/tmp/test.db"

# ایجاد دیتابیس
sqlite3 "$DB_FILE" << EOF
CREATE TABLE IF NOT EXISTS products (
    id INTEGER PRIMARY KEY,
    name TEXT,
    price REAL,
    category TEXT
);
EOF

# درج داده‌ها
sqlite3 "$DB_FILE" << EOF
INSERT INTO products (name, price, category) VALUES ('لپتاپ', 15000, 'الکترونیک');
INSERT INTO products (name, price, category) VALUES ('موبایل', 8000, 'الکترونیک');
INSERT INTO products (name, price, category) VALUES ('کتاب', 50, 'کتاب');
EOF

# فیلتر و مرتب‌سازی
echo -e "\nمحصولات الکترونیک:"
sqlite3 "$DB_FILE" "SELECT * FROM products WHERE category='الکترونیک';"

echo -e "\nمحصولات بالای 1000:"
sqlite3 "$DB_FILE" "SELECT * FROM products WHERE price > 1000;"

echo -e "\nمرتب شده بر اساس قیمت:"
sqlite3 "$DB_FILE" "SELECT * FROM products ORDER BY price DESC;"

# پاکسازی
rm "$DB_FILE"
```

### توضیح خط به خط:

- `WHERE category='الکترونیک'` - فیلتر بر اساس دسته‌بندی
- `WHERE price > 1000` - فیلتر بر اساس قیمت
- `ORDER BY price DESC` - مرتب‌سازی نزولی بر اساس قیمت

## MySQL/MariaDB

```bash
mysql -u user -p database -e "SQL_QUERY"
```

## مثال ۳: MySQL

فایل [examples/03-mysql.sh](examples/03-mysql.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# توجه: برای اجرای این اسکریپت، MySQL باید نصب باشد

echo "مثال MySQL:"
echo "mysql -u username -p database -e 'SELECT * FROM table;'"

# درج داده
echo "mysql -u username -p database -e \"INSERT INTO table (col1, col2) VALUES ('val1', 'val2');\""

# خواندن داده
echo "mysql -u username -p database -e 'SELECT * FROM table;'"
```

### توضیح خط به خط:

- `-u username` - نام کاربری
- `-p` - درخواست پسورد
- `-e` - اجرای دستور SQL
- برای استفاده واقعی، MySQL باید نصب و پیکربندی شده باشد

## PostgreSQL

```bash
psql -U user -d database -c "SQL_QUERY"
```

## مثال ۴: PostgreSQL

فایل [examples/04-postgresql.sh](examples/04-postgresql.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# توجه: برای اجرای این اسکریپت، PostgreSQL باید نصب باشد

echo "مثال PostgreSQL:"
echo "psql -U username -d database -c 'SELECT * FROM table;'"

# درج داده
echo "psql -U username -d database -c \"INSERT INTO table (col1, col2) VALUES ('val1', 'val2');\""

# خواندن داده
echo "psql -U username -d database -c 'SELECT * FROM table;'"
```

### توضیح خط به خط:

- `-U username` - نام کاربری
- `-d database` - نام دیتابیس
- `-c` - اجرای دستور SQL
- برای استفاده واقعی، PostgreSQL باید نصب و پیکربندی شده باشد

## اسکریپت مدیریت دیتابیس

## مثال ۵: اسکریپت مدیریت

فایل [examples/05-db-manager.sh](examples/05-db-manager.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

DB_FILE="/tmp/manager.db"

# تابع ایجاد دیتابیس
init_db() {
    sqlite3 "$DB_FILE" << EOF
CREATE TABLE IF NOT EXISTS tasks (
    id INTEGER PRIMARY KEY,
    title TEXT,
    status TEXT,
    created_at TEXT
);
EOF
    echo "دیتابیس ایجاد شد"
}

# تابع افزودن تسک
add_task() {
    local title=$1
    local status=$2
    local created_at=$(date '+%Y-%m-%d %H:%M:%S')
    
    sqlite3 "$DB_FILE" "INSERT INTO tasks (title, status, created_at) VALUES ('$title', '$status', '$created_at');"
    echo "تسک اضافه شد: $title"
}

# تابع لیست تسک‌ها
list_tasks() {
    echo "لیست تسک‌ها:"
    sqlite3 "$DB_FILE" "SELECT * FROM tasks;"
}

# تابع به‌روزرسانی تسک
update_task() {
    local id=$1
    local status=$2
    
    sqlite3 "$DB_FILE" "UPDATE tasks SET status='$status' WHERE id=$id;"
    echo "تسک $id به‌روزرسانی شد"
}

# تابع حذف تسک
delete_task() {
    local id=$1
    
    sqlite3 "$DB_FILE" "DELETE FROM tasks WHERE id=$id;"
    echo "تسک $id حذف شد"
}

# اجرای توابع
init_db
add_task "تسک ۱" "pending"
add_task "تسک ۲" "completed"
list_tasks
update_task 1 "completed"
list_tasks
delete_task 2
list_tasks

# پاکسازی
rm "$DB_FILE"
```

### توضیح خط به خط:

- توابع جداگانه برای هر عملیات
- `init_db` - ایجاد دیتابیس
- `add_task` - افزودن تسک
- `list_tasks` - نمایش لیست
- `update_task` - به‌روزرسانی
- `delete_task` - حذف

## مثال‌های عملی

### لاگ‌برداری در دیتابیس

```bash
#!/usr/bin/env bash
DB="/tmp/logs.db"
sqlite3 "$DB" "CREATE TABLE logs (id INTEGER PRIMARY KEY, message TEXT, timestamp TEXT);"
sqlite3 "$DB" "INSERT INTO logs (message, timestamp) VALUES ('Error occurred', '$(date)');"
```

## مثال ۶: لاگ‌برداری

فایل [examples/06-db-logging.sh](examples/06-db-logging.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

DB_FILE="/tmp/logs.db"

# ایجاد جدول لاگ
sqlite3 "$DB_FILE" << EOF
CREATE TABLE IF NOT EXISTS logs (
    id INTEGER PRIMARY KEY,
    level TEXT,
    message TEXT,
    timestamp TEXT
);
EOF

# تابع لاگ
log_message() {
    local level=$1
    local message=$2
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    
    sqlite3 "$DB_FILE" "INSERT INTO logs (level, message, timestamp) VALUES ('$level', '$message', '$timestamp');"
    echo "[$timestamp] [$level] $message"
}

# استفاده
log_message "INFO" "اسکریپت شروع شد"
log_message "ERROR" "خطا در اتصال"
log_message "INFO" "اسکریپت تمام شد"

# نمایش لاگ‌ها
echo -e "\nلاگ‌ها:"
sqlite3 "$DB_FILE" "SELECT * FROM logs;"

# پاکسازی
rm "$DB_FILE"
```

### توضیح خط به خط:

- جدول logs با level, message, timestamp
- تابع log_message برای افزودن لاگ
- timestamp خودکار با date
- نمایش تمام لاگ‌ها

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. فراموش کردن quote در SQL

❌ اشتباه:
```bash
sqlite3 db "SELECT * FROM users WHERE name=$name"  - خطر SQL injection
```

✅ درست:
```bash
sqlite3 db "SELECT * FROM users WHERE name='$name'"  - quote
```

### ۲. نادیده گرفتن خطاها

❌ اشتباه:
```bash
sqlite3 db "QUERY"  - خطا نادیده گرفته می‌شود
```

✅ درست:
```bash
sqlite3 db "QUERY" || echo "خطا در دیتابیس"
```

### ۳. فراموش کردن پاکسازی

❌ مشکل:
```bash
sqlite3 db "QUERY"  - فایل باقی می‌ماند
```

✅ بهتر:
```bash
sqlite3 db "QUERY"
rm db  - پاکسازی
```

## بهترین شیوه‌ها (Best Practices)

1. **همیشه از quote استفاده کنید** - برای جلوگیری از SQL injection
2. **خطاها را بررسی کنید** - برای مدیریت مناسب
3. **از transaction استفاده کنید** - برای داده‌های مهم
4. **backup بگیرید** - قبل از تغییرات مهم
5. **پاکسازی کنید** - فایل‌های موقت را حذف کنید

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- اتصال به SQLite
- اجرای دستورات SQL
- MySQL/MariaDB
- PostgreSQL
- اسکریپت مدیریت دیتابیس
- لاگ‌برداری در دیتابیس
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): مدیریت مخاطبین

یک اسکریپت بنویسید که:
1. یک دیتابیس SQLite ایجاد کند
2. جدول مخاطبین با نام، ایمیل، تلفن بسازد
3. امکان افزودن، لیست، و حذف مخاطب داشته باشد

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): مدیریت موجودی

یک اسکریپت بنویسید که:
1. دیتابیس موجودی کالا ایجاد کند
3. امکان افزودن، به‌روزرسانی، و گزارش موجودی داشته باشد

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، web scraping با Bash را یاد می‌گیریم.
