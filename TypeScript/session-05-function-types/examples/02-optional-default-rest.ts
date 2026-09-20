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

console.log(greet("سارا"));
console.log(greet("سارا", "مهندس"));
repeat("TS");
repeat("TS", 3);
console.log("sum:", sum(1, 2, 3, 4));
console.log("sum empty:", sum());
