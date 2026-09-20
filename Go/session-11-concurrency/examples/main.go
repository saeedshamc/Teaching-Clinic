package main

import (
	"fmt"
	"sync"
)

func square(n int, out chan<- int, wg *sync.WaitGroup) { defer wg.Done(); out <- n * n }
func main() {
	out := make(chan int)
	var wg sync.WaitGroup
	for _, n := range []int{2, 3, 4} {
		wg.Add(1)
		go square(n, out, &wg)
	}
	go func() { wg.Wait(); close(out) }()
	total := 0
	for value := range out {
		total += value
	}
	fmt.Println("sum:", total)
}
