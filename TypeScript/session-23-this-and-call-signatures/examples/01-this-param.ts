function greet(this: { name: string }): string {
  return `سلام، ${this.name}`;
}
console.log(greet.call({ name: "سارا" }));
