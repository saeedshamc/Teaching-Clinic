package main

import "testing"

func TestCounterInc(t *testing.T) {
	cases := []struct {
		name string
		incs int
		want int
	}{
		{"once", 1, 1},
		{"thrice", 3, 3},
	}
	for _, tc := range cases {
		t.Run(tc.name, func(t *testing.T) {
			c := &Counter{}
			var got int
			for i := 0; i < tc.incs; i++ {
				got = c.Inc()
			}
			if got != tc.want {
				t.Fatalf("got %d want %d", got, tc.want)
			}
		})
	}
}
