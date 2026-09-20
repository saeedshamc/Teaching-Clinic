package main

import "fmt"

func main() {
	nums := []int{10, 20}
	nums = append(nums, 30)
	m := map[string]int{"a": 1}
	if _, ok := m["b"]; !ok {
		m["b"] = 2
	}
	s := "سلام"
	for _, r := range s {
		fmt.Printf("%c ", r)
	}
	fmt.Println()
	fmt.Println(nums, m)
}
