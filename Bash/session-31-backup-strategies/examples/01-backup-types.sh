#!/usr/bin/env bash

echo "=== Backup Types ==="

# ایجاد دایرکتوری تست
mkdir -p /tmp/source /tmp/backups
echo "Test data" > /tmp/source/file1.txt
echo "More data" > /tmp/source/file2.txt

# Full backup
echo "1. Full Backup:"
tar -czf /tmp/backups/full-backup.tar.gz /tmp/source
echo "✓ Full backup ایجاد شد"
ls -lh /tmp/backups/full-backup.tar.gz

# تغییر داده
echo "Modified data" > /tmp/source/file1.txt
echo "New file" > /tmp/source/file3.txt

# Incremental backup
echo -e "\n2. Incremental Backup:"
touch /tmp/last-backup
tar -czf /tmp/backups/inc-backup.tar.gz --newer=/tmp/last-backup /tmp/source
echo "✓ Incremental backup ایجاد شد"
ls -lh /tmp/backups/inc-backup.tar.gz

# پاکسازی
rm -rf /tmp/source /tmp/backups /tmp/last-backup
