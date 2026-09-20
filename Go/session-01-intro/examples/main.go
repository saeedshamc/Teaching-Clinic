package main

import (
	"fmt"
	"runtime"
)

func main() {
	fmt.Printf("Go ????? ???: %s ??? %s/%s\n", runtime.Version(), runtime.GOOS, runtime.GOARCH)
}
