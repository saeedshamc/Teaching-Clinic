package main

import "fmt"

func MapSlice[T any, R any](in []T, f func(T) R) []R {
	out := make([]R, 0, len(in))
	for _, v := range in {
		out = append(out, f(v))
	}
	return out
}

func main() {
	nums := []int{1, 2, 3}
	strs := MapSlice(nums, func(n int) string {
		return fmt.Sprintf("#%d", n)
	})
	fmt.Println(strs)
}
