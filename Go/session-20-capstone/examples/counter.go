package main

type Counter struct {
	n int
}

func (c *Counter) Inc() int {
	c.n++
	return c.n
}

func (c *Counter) Value() int {
	return c.n
}

func main() {
	c := &Counter{}
	c.Inc()
	_ = c.Value()
}
