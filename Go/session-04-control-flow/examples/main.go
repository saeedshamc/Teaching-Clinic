package main

import "fmt"

func grade(score int) string {
	switch {
	case score < 0 || score > 100:
		return "???????"
	case score >= 90:
		return "A"
	case score >= 75:
		return "B"
	case score >= 60:
		return "C"
	default:
		return "D"
	}
}
func main() {
	for _, score := range []int{95, 72, -1, 61} {
		if g := grade(score); g != "???????" {
			fmt.Println(score, g)
		}
	}
}
