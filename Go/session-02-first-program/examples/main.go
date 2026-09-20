package main

import "fmt"

func Greet(name string) string {
	return "سلام، " + name
}

func main() {
	fmt.Println(Greet("Go"))
}
