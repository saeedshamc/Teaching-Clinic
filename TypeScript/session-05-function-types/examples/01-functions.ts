function add(a: number, b: number): number {
  return a + b;
}

function greet(name: string, title?: string): string {
  return title ? `${title} ${name}` : name;
}

function repeat(message: string, times: number = 2): void {
  for (let i = 0; i < times; i++) {
    console.log(message);
  }
}

function sum(...nums: number[]): number {
  return nums.reduce((acc, n) => acc + n, 0);
}

type Mapper = (value: number) => number;

const double: Mapper = (n) => n * 2;

console.log(add(2, 3));
console.log(greet("سارا"));
console.log(greet("سارا", "مهندس"));
repeat("TS", 3);
console.log("sum:", sum(1, 2, 3, 4));
console.log("double 7:", double(7));
