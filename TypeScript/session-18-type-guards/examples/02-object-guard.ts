type User = { id: number; name: string };

function isUser(x: unknown): x is User {
  if (typeof x !== "object" || x === null) return false;
  const o = x as Record<string, unknown>;
  return typeof o.id === "number" && typeof o.name === "string";
}

console.log(isUser({ id: 1, name: "علی" }));
console.log(isUser({ id: "1" }));
