package main

import (
	"fmt"
	"strconv"
)

func Map[T, R any](items []T, f func(T) R) []R {
	out := make([]R, 0, len(items))
	for _, item := range items {
		out = append(out, f(item))
	}
	return out
}
func IndexOf[T comparable](items []T, target T) int {
	for i, item := range items {
		if item == target {
			return i
		}
	}
	return -1
}
func main() { fmt.Println(Map([]int{2, 4, 6}, strconv.Itoa), IndexOf([]string{"a", "b"}, "b")) }
