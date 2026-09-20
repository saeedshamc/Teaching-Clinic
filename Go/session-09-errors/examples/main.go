package main

import (
	"errors"
	"fmt"
)

var ErrNotFound = errors.New("not found")

func findUser(id int) (string, error) {
	if id != 1 {
		return "", fmt.Errorf("find user %d: %w", id, ErrNotFound)
	}
	return "Mina", nil
}
func main() {
	_, err := findUser(7)
	if errors.Is(err, ErrNotFound) {
		fmt.Println("????? ???? ???")
	}
}
