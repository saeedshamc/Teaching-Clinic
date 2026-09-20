type Result<T> = { ok: true; value: T } | { ok: false; error: string };
type User = { id: number; name: string };
function parseUser(data: unknown): Result<User> {
  if (typeof data !== "object" || data === null) return { ok: false, error: "obj" };
  const o = data as Record<string, unknown>;
  if (typeof o.id !== "number" || typeof o.name !== "string") return { ok: false, error: "shape" };
  return { ok: true, value: { id: o.id, name: o.name } };
}
console.log(parseUser({ id: 1, name: "a" }));
