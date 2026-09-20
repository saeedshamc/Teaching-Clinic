function assertString(x: unknown): asserts x is string {
  if (typeof x !== "string") throw new Error("need string");
}

function demo(x: unknown): string {
  assertString(x);
  return x.trim();
}

console.log(demo("  hi  "));
