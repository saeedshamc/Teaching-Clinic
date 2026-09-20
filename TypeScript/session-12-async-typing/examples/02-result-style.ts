type Result<T> =
  | { ok: true; value: T }
  | { ok: false; error: string };

type UserDto = { id: number; name: string };

function isRecord(value: unknown): value is Record<string, unknown> {
  return typeof value === "object" && value !== null;
}

function parseUserResult(data: unknown): Result<UserDto> {
  if (!isRecord(data)) return { ok: false, error: "not object" };
  if (typeof data.id !== "number" || typeof data.name !== "string") {
    return { ok: false, error: "shape invalid" };
  }
  return { ok: true, value: { id: data.id, name: data.name } };
}

const a = parseUserResult({ id: 2, name: "نتیجه" });
const b = parseUserResult(null);
console.log(a);
console.log(b);
