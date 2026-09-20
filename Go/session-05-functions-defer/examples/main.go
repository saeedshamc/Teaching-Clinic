package main

import "fmt"

func Div(a, b int) (int, error) {
	if b == 0 {
		return 0, fmt.Errorf("division by zero")
	}
	return a / b, nil
}

func main() {
	defer fmt.Println("پایان main")
	q, err := Div(10, 2)
	if err != nil {
		fmt.Println("خطا:", err)
		return
	}
	fmt.Println("خارج‌قسمت:", q)
}
