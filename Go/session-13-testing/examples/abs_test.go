package main

import "testing"

func TestAbs(t *testing.T) {
	cases := []struct {
		name string
		in   int
		want int
	}{
		{"pos", 2, 2},
		{"neg", -2, 2},
		{"zero", 0, 0},
	}
	for _, tc := range cases {
		t.Run(tc.name, func(t *testing.T) {
			if got := Abs(tc.in); got != tc.want {
				t.Fatalf("got %d want %d", got, tc.want)
			}
		})
	}
}

func BenchmarkAbs(b *testing.B) {
	for i := 0; i < b.N; i++ {
		_ = Abs(-1)
	}
}
