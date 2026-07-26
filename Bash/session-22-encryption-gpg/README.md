# جلسه ۲۲: رمزنگاری و GPG (Encryption and GPG)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه رمزنگاری
- رمزنگاری symmetric و asymmetric
- استفاده از GPG
- ایجاد و مدیریت keyها
- رمزنگاری و رمزگشایی فایل‌ها
- امضای دیجیتال
- مثال‌های عملی

## مفاهیم پایه رمزنگاری

رمزنگاری دو نوع اصلی دارد:
- **Symmetric Encryption** - یک key برای رمزنگاری و رمزگشایی
- **Asymmetric Encryption** - دو key: public و private

## Symmetric Encryption با OpenSSL

```bash
openssl enc -aes-256-cbc -in file -out file.enc
openssl enc -aes-256-cbc -d -in file.enc -out file
```

## مثال ۱: Symmetric Encryption

فایل [examples/01-symmetric-encryption.sh](examples/01-symmetric-encryption.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
echo "این یک متن محرمانه است" > /tmp/secret.txt

echo "فایل اصلی:"
cat /tmp/secret.txt

# رمزنگاری با AES-256
echo -e "\nرمزنگاری با AES-256-CBC..."
openssl enc -aes-256-cbc -salt -in /tmp/secret.txt -out /tmp/secret.enc -k "mypassword"

echo "فایل رمزنگاری شده ایجاد شد"
ls -lh /tmp/secret.enc

# رمزگشایی
echo -e "\nرمزگشایی..."
openssl enc -aes-256-cbc -d -in /tmp/secret.enc -out /tmp/secret_decrypted.txt -k "mypassword"

echo "فایل رمزگشایی شده:"
cat /tmp/secret_decrypted.txt

# پاکسازی
rm /tmp/secret.txt /tmp/secret.enc /tmp/secret_decrypted.txt
```

### توضیح خط به خط:

- `openssl enc` - دستور رمزنگاری
- `-aes-256-cbc` - الگوریتم AES-256 با CBC
- `-salt` - افزودن salt برای امنیت بیشتر
- `-k password` - key برای رمزنگاری
- `-d` - decrypt (رمزگشایی)

## GPG - GNU Privacy Guard

GPG ابزار استاندارد برای رمزنگاری asymmetric است.

```bash
gpg --gen-key           - ایجاد key جدید
gpg --list-keys         - لیست keyها
gpg --encrypt file      - رمزنگاری
gpg --decrypt file      - رمزگشایی
```

## مثال ۲: ایجاد GPG Key

فایل [examples/02-gpg-keygen.sh](examples/02-gpg-keygen.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "ایجاد GPG Key..."

# ایجاد key با تنظیمات پیش‌فرض
# توجه: این دستور تعاملی است
echo "برای ایجاد key، دستور زیر را اجرا کنید:"
echo "gpg --full-generate-key"
echo ""
echo "تنظیمات پیشنهادی:"
echo "- Key type: RSA and RSA"
echo "- Key size: 4096"
echo "- Validity: 1y"
echo "- Real name: Your Name"
echo "- Email: your@email.com"
echo "- Passphrase: strong password"

# لیست keyهای موجود
echo -e "\nKeyهای موجود:"
gpg --list-keys 2>/dev/null || echo "هیچ keyی وجود ندارد"
```

### توضیح خط به خط:

- `gpg --full-generate-key` - ایجاد key با تنظیمات کامل
- Key type RSA برای سازگاری
- Key size 4096 برای امنیت بالا
- Passphrase برای محافظت از key

## رمزنگاری با GPG

## مثال ۳: رمزنگاری GPG

فایل [examples/03-gpg-encrypt.sh](examples/03-gpg-encrypt.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
echo "این متن محرمانه با GPG رمزنگاری شده است" > /tmp/secret.txt

echo "فایل اصلی:"
cat /tmp/secret.txt

# رمزنگاری با symmetric key (بدون نیاز به key pair)
echo -e "\nرمزنگاری symmetric با GPG..."
gpg --symmetric --cipher-algo AES256 /tmp/secret.txt

echo "فایل رمزنگاری شده ایجاد شد"
ls -lh /tmp/secret.txt.gpg

# رمزگشایی
echo -e "\nرمزگشایی..."
gpg --decrypt /tmp/secret.txt.gpg > /tmp/secret_decrypted.txt

echo "فایل رمزگشایی شده:"
cat /tmp/secret_decrypted.txt

# پاکسازی
rm /tmp/secret.txt /tmp/secret.txt.gpg /tmp/secret_decrypted.txt
```

### توضیح خط به خط:

- `gpg --symmetric` - رمزنگاری symmetric
- `--cipher-algo AES256` - الگوریتم AES-256
- `.gpg` - پسوند فایل رمزنگاری شده
- `gpg --decrypt` - رمزگشایی

## رمزنگاری Asymmetric با GPG

## مثال ۴: Asymmetric Encryption

فایل [examples/04-asymmetric-encrypt.sh](examples/04-asymmetric-encrypt.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "رمزنگاری Asymmetric با GPG"
echo ""
echo "مراحل:"
echo "1. ایجاد key pair: gpg --full-generate-key"
echo "2. رمزنگاری برای recipient:"
echo "   gpg --encrypt --recipient user@email.com file.txt"
echo "3. رمزگشایی:"
echo "   gpg --decrypt file.gpg > file.txt"
echo ""
echo "مثال عملی:"
echo "gpg --encrypt --recipient your@email.com /tmp/secret.txt"
echo "gpg --decrypt /tmp/secret.txt.gpg > /tmp/decrypted.txt"
```

### توضیح خط به خط:

- `--recipient` - email گیرنده
- از public key گیرنده برای رمزنگاری
- فقط private key گیرنده می‌تواند رمزگشایی کند

## امضای دیجیتال

## مثال ۵: Digital Signature

فایل [examples/05-digital-signature.sh](examples/05-digital-signature.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
echo "این فایل امضای دیجیتال دارد" > /tmp/document.txt

echo "فایل اصلی:"
cat /tmp/document.txt

# امضای فایل
echo -e "\nامضای فایل..."
gpg --default-key your@email.com --detach-sign /tmp/document.txt

echo "امضای دیجیتال ایجاد شد"
ls -lh /tmp/document.txt.sig

# تأیید امضا
echo -e "\nتأیید امضا:"
gpg --verify /tmp/document.txt.sig /tmp/document.txt 2>&1 || echo "Key برای تأیید وجود ندارد"

# پاکسازی
rm /tmp/document.txt /tmp/document.txt.sig
```

### توضیح خط به خط:

- `--detach-sign` - ایجاد فایل امضای جداگانه
- `--default-key` - key برای امضا
- `--verify` - تأیید امضا
- فایل `.sig` حاوی امضای دیجیتال است

## مدیریت Keyها

## مثال ۶: Key Management

فایل [examples/06-key-management.sh](examples/06-key-management.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== مدیریت GPG Keyها ==="

# لیست keyهای public
echo -e "\n1. Keyهای Public:"
gpg --list-keys

# لیست keyهای private
echo -e "\n2. Keyهای Private:"
gpg --list-secret-keys

# Export public key
echo -e "\n3. Export Public Key:"
echo "gpg --export --armor your@email.com > public_key.asc"

# Import public key
echo -e "\n4. Import Public Key:"
echo "gpg --import public_key.asc"

# Delete key
echo -e "\n5. حذف Key:"
echo "gpg --delete-secret-key your@email.com"
echo "gpg --delete-key your@email.com"
```

### توضیح خط به خط:

- `--list-keys` - لیست public keys
- `--list-secret-keys` - لیست private keys
- `--export` - export key
- `--import` - import key
- `--delete-key` - حذف key

## نکات امنیتی مهم

### ۱. محافظت از Private Key

```bash
# همیشه passphrase قوی استفاده کنید
# private key را هرگز به اشتراک نگذارید
# backup از private key در مکان امن نگه دارید
```

### ۲. استفاده از Keyهای قوی

```bash
# حداقل 4096 bit برای RSA
# استفاده از passphrase قوی
# منقضی شدن key را تنظیم کنید
```

### ۳. تأیید Identity

```bash
# همیشه fingerprint را تأیید کنید
gpg --fingerprint your@email.com
```

## مثال ۷: امنیت Key

فایل [examples/07-key-security.sh](examples/07-key-security.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== امنیت GPG Key ==="

# نمایش fingerprint
echo -e "\n1. Fingerprint Keyها:"
gpg --list-keys --with-fingerprint

# بررسی trust level
echo -e "\n2. Trust Level:"
gpg --list-keys --with-colons | grep "^pub"

# تغییر passphrase
echo -e "\n3. تغییر Passphrase:"
echo "gpg --edit-key your@email.com"
echo "در gpg prompt: passwd"

# Revocation certificate
echo -e "\n4. ایجاد Revocation Certificate:"
echo "gpg --generate-revocation your@email.com > revoke.asc"
echo "این فایل را در مکان امن نگه دارید"
```

### توضیح خط به خط:

- `--with-fingerprint` - نمایش fingerprint
- `--with-colons` - فرمت machine-readable
- `--edit-key` - ویرایش key
- `--generate-revocation` - ایجاد certificate برای لغو key

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. فراموش کردن Passphrase

❌ اشتباه:
```bash
# passphrase ساده یا فراموش شده
```

✅ درست:
```bash
# passphrase قوی و ذخیره شده در password manager
```

### ۲. به اشتراک گذاشتن Private Key

❌ خطرناک:
```bash
# ارسال private key به دیگران
```

✅ درست:
```bash
# فقط public key را به اشتراک بگذارید
```

### ۳. استفاده از Keyهای ضعیف

❌ اشتباه:
```bash
# RSA 1024 یا کمتر
```

✅ درست:
```bash
# RSA 4096 یا بیشتر
```

## بهترین شیوه‌ها (Best Practices)

1. **همیشه passphrase قوی استفاده کنید** - حداقل 16 کاراکتر
2. **private key را محرمانه نگه دارید** - هرگز به اشتراک نگذارید
3. **backup از keyها بگیرید** - در مکان امن
4. **fingerprint را تأیید کنید** - قبل از استفاده
5. **keyهای قدیمی را revoke کنید** - پس از منقضی شدن

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه رمزنگاری
- Symmetric encryption با OpenSSL
- ایجاد و مدیریت GPG keyها
- رمزنگاری symmetric و asymmetric
- امضای دیجیتال
- مدیریت keyها
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): رمزنگاری فایل

یک اسکریپت بنویسید که:
1. یک فایل متنی ایجاد کند
2. با AES-256 رمزنگاری کند
3. رمزگشایی کند
4. محتوا را تأیید کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): مدیریت GPG

یک اسکریپت بنویسید که:
1. لیست keyهای GPG را نمایش دهد
2. یک فایل را رمزنگاری کند
3. امضای دیجیتال ایجاد کند
4. تأیید امضا کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، مبانی Cloud را یاد می‌گیریم.
