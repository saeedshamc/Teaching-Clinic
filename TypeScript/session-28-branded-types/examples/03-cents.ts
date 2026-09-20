type Cents = number & { readonly __brand: "Cents" };
function cents(n: number): Cents {
  if (!Number.isInteger(n)) throw new Error("int");
  return n as Cents;
}
function add(a: Cents, b: Cents): Cents { return (a + b) as Cents; }
console.log(add(cents(100), cents(50)));
