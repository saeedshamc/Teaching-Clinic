package main

import "fmt"

const taxPercent = 9

func main() {
	prices := []int64{120_000, 80_000, 50_000}
	var total int64
	for _, price := range prices {
		total += price
	}
	tax := total * taxPercent / 100
	fmt.Printf("???: %d? ??????: %d? ???????: %.1f\n", total, tax, float64(total)/float64(len(prices)))
}
