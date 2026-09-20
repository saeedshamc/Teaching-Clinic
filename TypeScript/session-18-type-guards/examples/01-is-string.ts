function isString(x: unknown): x is string {
  return typeof x === "string";
}

function upper(x: unknown): string {
  if (isString(x)) return x.toUpperCase();
  return "";
}

console.log(upper("ts"), upper(1));
