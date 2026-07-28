#!/usr/bin/env bash

# تمرین ۱: Documentation
# هدف: یک اسکریپت بنویسید که:
# 1. README را ایجاد کند
# 2. code documentation را اضافه کند
# 3. examples را document کند

# راه‌حل:
echo "=== Documentation Exercise ==="

# تابع create documented script
create_documented_script() {
    local script_name=$1
    
    echo "1. Creating documented script: $script_name"
    
    cat > "${script_name}.sh" << 'EOF'
#!/usr/bin/env bash
#
# Utility Script
# A collection of useful utility functions
#
# Author: Your Name
# Version: 1.0.0
# License: MIT
#

#
# Calculate the sum of two numbers
#
# Args:
#   $1 - First number (integer)
#   $2 - Second number (integer)
# Returns:
#   Sum of the two numbers
# Example:
#   calculate_sum 5 3  # returns 8
#
calculate_sum() {
    local num1=$1
    local num2=$2
    
    # Validate inputs
    if ! [[ "$num1" =~ ^[0-9]+$ ]] || ! [[ "$num2" =~ ^[0-9]+$ ]]; then
        echo "Error: Both arguments must be integers" >&2
        return 1
    fi
    
    echo $(($num1 + $num2))
}

#
# Check if a file exists and is readable
#
# Args:
#   $1 - File path to check
# Returns:
#   0 if file exists and is readable, 1 otherwise
# Example:
#   check_file "/etc/passwd"  # returns 0
#
check_file() {
    local filepath=$1
    
    if [ -z "$filepath" ]; then
        echo "Error: File path is required" >&2
        return 1
    fi
    
    if [ -f "$filepath" ] && [ -r "$filepath" ]; then
        echo "File exists and is readable: $filepath"
        return 0
    else
        echo "File does not exist or is not readable: $filepath" >&2
        return 1
    fi
}

#
# Main function - entry point of the script
#
# Usage:
#   ./script.sh <command> [args]
#
main() {
    local command=$1
    shift
    
    case "$command" in
        "sum")
            calculate_sum "$@"
            ;;
        "check")
            check_file "$@"
            ;;
        *)
            echo "Usage: $0 {sum|check} [args]"
            exit 1
            ;;
    esac
}

# Execute main function if script is run directly
if [ "${BASH_SOURCE[0]}" = "${0}" ]; then
    main "$@"
fi
EOF
    
    chmod +x "${script_name}.sh"
    echo "✓ Documented script created: ${script_name}.sh"
}

# تابع create README
create_readme() {
    local project_name=$1
    
    echo -e "\n2. Creating README for $project_name"
    
    cat > README.md << EOF
# $project_name

## Description
A collection of useful utility functions for everyday tasks.

## Installation

\`\`\`bash
# Clone the repository
git clone https://github.com/username/$project_name.git
cd $project_name

# Make the script executable
chmod +x utility.sh
\`\`\`

## Usage

\`\`\`bash
# Calculate sum of two numbers
./utility.sh sum 5 3

# Check if a file exists
./utility.sh check /etc/passwd
\`\`\`

## Functions

### calculate_sum
Calculate the sum of two numbers.

\`\`\`bash
calculate_sum <num1> <num2>
\`\`\`

**Parameters:**
- \`num1\` - First number (integer)
- \`num2\` - Second number (integer)

**Returns:** Sum of the two numbers

**Example:**
\`\`\`bash
calculate_sum 5 3  # Output: 8
\`\`\`

### check_file
Check if a file exists and is readable.

\`\`\`bash
check_file <filepath>
\`\`\`

**Parameters:**
- \`filepath\` - Path to the file

**Returns:** 0 if file exists and is readable, 1 otherwise

**Example:**
\`\`\`bash
check_file /etc/passwd  # Output: File exists and is readable
\`\`\`

## Requirements

- Bash 4.0 or higher
- No external dependencies

## License

MIT License - see LICENSE file for details
EOF
    
    echo "✓ README.md created"
}

# تابع create examples documentation
create_examples_doc() {
    echo -e "\n3. Creating examples documentation"
    
    mkdir -p examples
    
    cat > examples/EXAMPLES.md << 'EOF'
# Examples

This document provides detailed examples of using the utility script.

## Example 1: Batch Processing

Calculate sums for multiple numbers:

\`\`\`bash
#!/bin/env bash

numbers=(10 20 30 40 50)
for num in "${numbers[@]}"; do
    result=$(../utility.sh sum $num 5)
    echo "$num + 5 = $result"
done
\`\`\`

## Example 2: File Validation

Validate multiple files:

\`\`\`bash
#!/bin/env bash

files=("/etc/passwd" "/etc/hosts" "/nonexistent")
for file in "${files[@]}"; do
    ../utility.sh check "$file"
done
\`\`\`

## Example 3: Integration with Other Scripts

Use in a larger script:

\`\`\`bash
#!/bin/env bash

source ./utility.sh

# Use functions directly
total=$(calculate_sum 100 200)
echo "Total: $total"

check_file "/etc/config"
\`\`\`
EOF
    
    echo "✓ Examples documentation created"
}

# تابع create API documentation
create_api_doc() {
    echo -e "\n4. Creating API documentation"
    
    mkdir -p docs/api
    
    cat > docs/api/functions.md << 'EOF'
# API Reference

## Functions

### calculate_sum(num1, num2)

Calculate the sum of two integers.

**Parameters:**
- \`num1\` (integer): First number
- \`num2\` (integer): Second number

**Returns:** (integer) Sum of the two numbers

**Errors:**
- Returns error if arguments are not integers

**Example:**
\`\`\`bash
result=$(calculate_sum 5 3)
echo $result  # Output: 8
\`\`\`

### check_file(filepath)

Check if a file exists and is readable.

**Parameters:**
- \`filepath\` (string): Path to the file

**Returns:** (integer) 0 if success, 1 if failure

**Errors:**
- Returns error if filepath is empty
- Returns error if file doesn't exist or is not readable

**Example:**
\`\`\`bash
if check_file "/etc/passwd"; then
    echo "File is accessible"
fi
\`\`\`
EOF
    
    echo "✓ API documentation created"
}

# تابع show documentation structure
show_structure() {
    echo -e "\n5. Documentation structure:"
    echo "  project/"
    echo "    ├── README.md"
    echo "    ├── utility.sh (documented)"
    echo "    ├── examples/"
    echo "    │   └── EXAMPLES.md"
    echo "    └── docs/"
    echo "        └── api/"
    echo "            └── functions.md"
}

# اجرا
PROJECT_NAME="bash-utilities"

create_documented_script "utility"
create_readme "$PROJECT_NAME"
create_examples_doc
create_api_doc
show_structure
