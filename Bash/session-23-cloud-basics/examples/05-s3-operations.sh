#!/usr/bin/env bash

echo "=== عملیات S3 ==="

# ایجاد bucket
echo -e "\n1. ایجاد S3 Bucket:"
echo "aws s3 mb s3://my-unique-bucket-name-$(date +%s)"

# لیست buckets
echo -e "\n2. لیست Buckets:"
aws s3 ls 2>/dev/null || echo "نیاز به credentials"

# آپلود فایل
echo -e "\n3. آپلود فایل:"
echo "aws s3 cp /tmp/file.txt s3://my-bucket/"

# دانلود فایل
echo -e "\n4. دانلود فایل:"
echo "aws s3 cp s3://my-bucket/file.txt /tmp/downloaded.txt"

# حذف فایل
echo -e "\n5. حذف فایل:"
echo "aws s3 rm s3://my-bucket/file.txt"

# حذف bucket
echo -e "\n6. حذف Bucket:"
echo "aws s3 rb s3://my-bucket/"
