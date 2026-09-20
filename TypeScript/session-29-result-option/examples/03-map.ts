type Result<T, E = string> =
  | { ok: true; value: T }
  | { ok: false; error: E };
function mapResult<T, U, E>(r: Result<T, E>, fn: (v: T) => U): Result<U, E> {
  if (!r.ok) return r;
  return { ok: true, value: fn(r.value) };
}
console.log(mapResult({ ok: true, value: 2 }, (n) => n * 10));
