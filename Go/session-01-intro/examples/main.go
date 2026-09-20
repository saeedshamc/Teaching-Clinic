package main

import (
	"fmt"
	"runtime"
)

func main() {
	fmt.Printf("نسخه Go: %s | سیستم: %s/%s\n",
		runtime.Version(), runtime.GOOS, runtime.GOARCH)
}
