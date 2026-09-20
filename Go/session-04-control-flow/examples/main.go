package main

import "fmt"

func Grade(score int) string {
	switch {
	case score >= 90:
		return "A"
	case score >= 70:
		return "B"
	default:
		return "C"
	}
}

func main() {
	sum := 0
	for i := 1; i <= 5; i++ {
		sum += i
	}
	fmt.Println(sum, Grade(85))
}
