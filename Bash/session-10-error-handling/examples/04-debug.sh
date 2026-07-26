#!/usr/bin/env bash

# روش ۱: set -x
echo "روش ۱: set -x"
set -x
VAR1="test"
VAR2="hello"
echo "$VAR1 $VAR2"
set +x

# روش ۲: echo برای دیباگ
echo -e "\nروش ۲: echo برای دیباگ"
debug() {
    echo "[DEBUG] $*" >&2
}

NAME="علی"
debug "NAME = $NAME"
AGE=25
debug "AGE = $AGE"

# روش ۳: bash -x
echo -e "\nروش ۳: اجرا با bash -x"
echo "برای تست: bash -x script.sh"
