function format(x: number): string;
function format(x: string): string;
function format(x: number | string): string {
  if (typeof x === "number") return `#${x}`;
  return x.trim();
}
console.log(format(7), format("  a  "));
