#!/usr/bin/env bash

NAME="Ali"
AGE=25

# استفاده از && و || در [[ ]]
if [[ $AGE -ge 18 && $NAME == "Ali" ]]; then
    echo "شرط برقرار است"
fi

# تطبیق الگو
if [[ $NAME == A* ]]; then
    echo "نام با A شروع می‌شود"
fi

# تطبیق با regex
if [[ $NAME =~ ^[A-Z] ]]; then
    echo "نام با حرف بزرگ شروع می‌شود"
fi
