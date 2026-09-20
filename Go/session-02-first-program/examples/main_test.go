package main

import "testing"

func TestGreet(t *testing.T) {
	got := Greet("Sara")
	want := "سلام، Sara"
	if got != want {
		t.Fatalf("got %q want %q", got, want)
	}
}
