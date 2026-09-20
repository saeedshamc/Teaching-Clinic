package main

import (
	"context"
	"fmt"
	"time"
)

func Work(ctx context.Context) error {
	select {
	case <-time.After(3 * time.Second):
		fmt.Println("تمام")
		return nil
	case <-ctx.Done():
		return ctx.Err()
	}
}

func main() {
	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()
	if err := Work(ctx); err != nil {
		fmt.Println("لغو/مهلت:", err)
	}
}
