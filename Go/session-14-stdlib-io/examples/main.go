package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	path := "sample.txt"
	content := "خط یک\n\nخط دو\n"
	if err := os.WriteFile(path, []byte(content), 0o644); err != nil {
		panic(err)
	}
	defer os.Remove(path)

	f, err := os.Open(path)
	if err != nil {
		panic(err)
	}
	defer f.Close()

	sc := bufio.NewScanner(f)
	n := 0
	for sc.Scan() {
		if strings.TrimSpace(sc.Text()) != "" {
			n++
		}
	}
	if err := sc.Err(); err != nil {
		panic(err)
	}
	fmt.Println("خطوط غیرخالی:", n)
}
