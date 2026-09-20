package main

import (
	"fmt"
	"sort"
)

func main() {
	text := "???? Go? ????"
	counts := map[rune]int{}
	for _, r := range text {
		if r != ' ' && r != '?' {
			counts[r]++
		}
	}
	keys := make([]rune, 0, len(counts))
	for r := range counts {
		keys = append(keys, r)
	}
	sort.Slice(keys, func(i, j int) bool { return keys[i] < keys[j] })
	for _, r := range keys {
		fmt.Printf("%c: %d\n", r, counts[r])
	}
}
