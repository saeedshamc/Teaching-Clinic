function head(s: string): string;
function head<T>(arr: T[]): T | undefined;
function head(x: string | unknown[]): unknown {
  return x[0];
}
console.log(head("xyz"), head([10, 20]));
