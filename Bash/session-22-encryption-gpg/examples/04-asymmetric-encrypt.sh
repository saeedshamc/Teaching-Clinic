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
