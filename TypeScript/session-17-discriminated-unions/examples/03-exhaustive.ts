type Kind = "a" | "b";

function assertNever(x: never): never {
  throw new Error(String(x));
}

function label(k: Kind): string {
  switch (k) {
    case "a":
      return "A";
    case "b":
      return "B";
    default:
      return assertNever(k);
  }
}

console.log(label("a"), label("b"));
