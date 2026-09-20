package main

import (
	"encoding/json"
	"fmt"
	"strconv"
	"time"
)

type User struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
}

func main() {
	u := User{Name: "Sara", Age: 20}
	b, err := json.Marshal(u)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(b))

	n, err := strconv.Atoi("42")
	if err != nil {
		panic(err)
	}
	fmt.Println("n=", n)

	t, err := time.Parse(time.RFC3339, "2024-01-02T15:04:05Z")
	if err != nil {
		panic(err)
	}
	fmt.Println(t.Format("2006-01-02"))
}
