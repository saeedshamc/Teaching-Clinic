#!/usr/bin/env bash

echo "=== Code Documentation ==="

# تابع با documentation کامل
#
# این تابع دو عدد را جمع می‌کند
# Args:
#   $1 - عدد اول (integer)
#   $2 - عدد دوم (integer)
# Returns:
#   مجموع دو عدد (integer)
# Example:
#   add_numbers 5 3  # returns 8
#
add_numbers() {
    local num1=$1
    local num2=$2
    
    # Validate inputs
    if ! [[ "$num1" =~ ^[0-9]+$ ]] || ! [[ "$num2" =~ ^[0-9]+$ ]]; then
        echo "Error: Arguments must be integers" >&2
        return 1
    fi
    
    echo $(($num1 + $num2))
}

# تابع با documentation برای string operations
#
# این تابع یک رشته را معکوس می‌کند
# Args:
#   $1 - رشته ورودی
# Returns:
#   رشته معکوس شده
# Example:
#   reverse_string "hello"  # returns "olleh"
#
reverse_string() {
    local str=$1
    local reversed=""
    local len=${#str}
    
    # Loop through string in reverse
    for ((i=len-1; i>=0; i--)); do
        reversed+="${str:$i:1}"
    done
    
    echo "$reversed"
}

# تابع با documentation برای file operations
#
# این تابع یک فایل را ایجاد می‌کند و محتوا را در آن می‌نویسد
# Args:
#   $1 - نام فایل
#   $2 - محتوا
# Returns:
#   0 در صورت موفقیت، 1 در صورت شکست
# Example:
#   write_file "test.txt" "Hello World"
#
write_file() {
    local filename=$1
    local content=$2
    
    # Check if filename is provided
    if [ -z "$filename" ]; then
        echo "Error: Filename is required" >&2
        return 1
    fi
    
    # Write content to file
    echo "$content" > "$filename"
    
    if [ $? -eq 0 ]; then
        echo "File created: $filename"
        return 0
    else
        echo "Error: Failed to create file" >&2
        return 1
    fi
}

# تابع برای نمایش documentation
show_function_docs() {
    local func_name=$1
    
    echo "Documentation for: $func_name"
    echo "==========================="
    
    case "$func_name" in
        "add_numbers")
            echo "Purpose: Add two numbers"
            echo "Usage: add_numbers <num1> <num2>"
            echo "Example: add_numbers 5 3"
            ;;
        "reverse_string")
            echo "Purpose: Reverse a string"
            echo "Usage: reverse_string <string>"
            echo "Example: reverse_string 'hello'"
            ;;
        "write_file")
            echo "Purpose: Write content to a file"
            echo "Usage: write_file <filename> <content>"
            echo "Example: write_file 'test.txt' 'Hello'"
            ;;
    esac
}

# استفاده
echo "1. Testing add_numbers:"
result=$(add_numbers 5 3)
echo "   Result: $result"

echo -e "\n2. Testing reverse_string:"
reversed=$(reverse_string "hello")
echo "   Reversed: $reversed"

echo -e "\n3. Testing write_file:"
write_file "/tmp/test.txt" "Hello from script"

echo -e "\n4. Showing function documentation:"
show_function_docs "add_numbers"
