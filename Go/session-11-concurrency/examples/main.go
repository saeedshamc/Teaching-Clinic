package main

import "fmt"

func main() {
	ch := make(chan string, 1)
	go func() {
		ch <- "آماده"
	}()
	fmt.Println(<-ch)
}
