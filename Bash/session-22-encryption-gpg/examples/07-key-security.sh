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
