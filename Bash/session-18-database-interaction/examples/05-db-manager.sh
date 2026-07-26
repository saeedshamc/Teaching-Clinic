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
