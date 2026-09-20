function first<T>(items: T[]): T | undefined {
  return items[0];
}

function identity<T>(value: T): T {
  return value;
}

console.log(first([10, 20, 30]));
console.log(first(["a", "b"]));
console.log(identity("سلام"));
