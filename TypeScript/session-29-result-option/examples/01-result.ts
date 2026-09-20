type Result<T, E = string> =
  | { ok: true; value: T }
  | { ok: false; error: E };
function parseEven(n: number): Result<number> {
  if (n % 2 !== 0) return { ok: false, error: "odd" };
  return { ok: true, value: n };
}
console.log(parseEven(2), parseEven(3));
