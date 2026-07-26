#!/usr/bin/env bash

# تمرین ۲: مدیریت S3
# هدف: یک اسکریپت بنویسید که:
# 1. یک S3 bucket ایجاد کند
# 2. فایل آپلود کند
# 3. لیست فایل‌ها را نمایش دهد
# 4. cleanup انجام دهد

# راه‌حل:
echo "=== مدیریت S3 ==="

# بررسی اتصال AWS
if ! aws sts get-caller-identity &>/dev/null; then
    echo "خطا: AWS متصل نیست"
    echo "ابتدا با 'aws configure' تنظیم کنید"
    exit 1
fi

# ایجاد نام bucket منحصر به فرد
BUCKET_NAME="test-bucket-$(date +%s)-$RANDOM"
echo "نام Bucket: $BUCKET_NAME"

# 1. ایجاد bucket
echo -e "\n1. ایجاد S3 Bucket:"
aws s3 mb "s3://$BUCKET_NAME" || { echo "خطا در ایجاد bucket"; exit 1; }
echo "✓ Bucket ایجاد شد"

# ایجاد فایل نمونه
echo "این یک فایل تست برای S3 است" > /tmp/test_file.txt

# 2. آپلود فایل
echo -e "\n2. آپلود فایل:"
aws s3 cp /tmp/test_file.txt "s3://$BUCKET_NAME/test_file.txt" || { echo "خطا در آپلود"; exit 1; }
echo "✓ فایل آپلود شد"

# 3. لیست فایل‌ها
echo -e "\n3. لیست فایل‌ها در bucket:"
aws s3 ls "s3://$BUCKET_NAME/" || echo "خطا در لیست"

# 4. دانلود فایل
echo -e "\n4. دانلود فایل:"
aws s3 cp "s3://$BUCKET_NAME/test_file.txt" /tmp/downloaded_file.txt || { echo "خطا در دانلود"; exit 1; }
echo "✓ فایل دانلود شد"

# تأیید محتوا
echo -e "\n5. تأیید محتوا:"
ORIGINAL=$(cat /tmp/test_file.txt)
DOWNLOADED=$(cat /tmp/downloaded_file.txt)
if [ "$ORIGINAL" = "$DOWNLOADED" ]; then
    echo "✓ محتوا مطابقت دارد"
else
    echo "✗ محتوا مطابقت ندارد"
fi

# 5. Cleanup
echo -e "\n6. Cleanup:"
aws s3 rm "s3://$BUCKET_NAME/test_file.txt" || echo "خطا در حذف فایل"
aws s3 rb "s3://$BUCKET_NAME" || echo "خطا در حذف bucket"
rm /tmp/test_file.txt /tmp/downloaded_file.txt
echo "✓ Cleanup کامل شد"
