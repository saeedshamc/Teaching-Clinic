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
