class Counter {
  private value = 0;

  inc(): void {
    this.value += 1;
  }

  getValue(): number {
    return this.value;
  }
}

const c = new Counter();
c.inc();
c.inc();
console.log("counter:", c.getValue());
