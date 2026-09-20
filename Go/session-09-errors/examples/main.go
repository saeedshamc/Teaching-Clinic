package main

import (
	"errors"
	"fmt"
)

var ErrNotFound = errors.New("not found")

func Find(id string) error {
	return fmt.Errorf("user %s: %w", id, ErrNotFound)
}

func main() {
	err := Find("u-1")
	if errors.Is(err, ErrNotFound) {
		fmt.Println("پیدا نشد:", err)
	}
}
