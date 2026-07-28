#!/usr/bin/env bash

echo "=== Unit Testing ==="

# تابع to test
add() {
    echo $(($1 + $2))
}

multiply() {
    echo $(($1 * $2))
}

divide() {
    if [ $2 -eq 0 ]; then
        echo "Error: Division by zero"
        return 1
    fi
    echo $(($1 / $2))
}

is_even() {
    if [ $(($1 % 2)) -eq 0 ]; then
        echo "true"
    else
        echo "false"
    fi
}

# تابع assert
assert() {
    local condition=$1
    local message=$2
    
    if eval "$condition"; then
        echo "✓ PASS: $message"
    else
        echo "✗ FAIL: $message"
        return 1
    fi
}

# تابع test add
test_add() {
    echo "--- Testing add() ---"
    assert "[ $(add 2 3) -eq 5 ]" "add(2, 3) = 5"
    assert "[ $(add 0 0) -eq 0 ]" "add(0, 0) = 0"
    assert "[ $(add -1 1) -eq 0 ]" "add(-1, 1) = 0"
    assert "[ $(add 100 200) -eq 300 ]" "add(100, 200) = 300"
}

# تابع test multiply
test_multiply() {
    echo -e "\n--- Testing multiply() ---"
    assert "[ $(multiply 2 3) -eq 6 ]" "multiply(2, 3) = 6"
    assert "[ $(multiply 0 5) -eq 0 ]" "multiply(0, 5) = 0"
    assert "[ $(multiply -2 3) -eq -6 ]" "multiply(-2, 3) = -6"
}

# تابع test divide
test_divide() {
    echo -e "\n--- Testing divide() ---"
    assert "[ $(divide 10 2) -eq 5 ]" "divide(10, 2) = 5"
    assert "[ $(divide 0 5) -eq 0 ]" "divide(0, 5) = 0"
    assert "[ $(divide 7 2) -eq 3 ]" "divide(7, 2) = 3 (integer division)"
}

# تابع test is_even
test_is_even() {
    echo -e "\n--- Testing is_even() ---"
    assert "[ $(is_even 2) = 'true' ]" "is_even(2) = true"
    assert "[ $(is_even 3) = 'false' ]" "is_even(3) = false"
    assert "[ $(is_even 0) = 'true' ]" "is_even(0) = true"
    assert "[ $(is_even -2) = 'true' ]" "is_even(-2) = true"
}

# تابع test edge cases
test_edge_cases() {
    echo -e "\n--- Testing edge cases ---"
    assert "[ $(add 999999 1) -eq 1000000 ]" "large numbers"
    assert "[ $(multiply 1 1) -eq 1 ]" "identity"
}

# استفاده
test_add
test_multiply
test_divide
test_is_even
test_edge_cases
