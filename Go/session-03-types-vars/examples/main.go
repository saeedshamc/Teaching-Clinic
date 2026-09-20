package main

import "fmt"

func main() {
	var ready bool
	count := 3
	const Title = "clinic"
	price := float64(count) * 1.5
	fmt.Println(Title, ready, count, price)
}
