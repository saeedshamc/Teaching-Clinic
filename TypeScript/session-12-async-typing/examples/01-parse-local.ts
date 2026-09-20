type UserDto = {
  id: number;
  name: string;
};

function isRecord(value: unknown): value is Record<string, unknown> {
  return typeof value === "object" && value !== null;
}

export function parseUser(data: unknown): UserDto {
  if (!isRecord(data)) {
    throw new Error("user must be object");
  }
  if (typeof data.id !== "number" || typeof data.name !== "string") {
    throw new Error("user shape invalid");
  }
  return { id: data.id, name: data.name };
}

const local = parseUser({ id: 1, name: "محلی" });
console.log("local:", local);

try {
  parseUser({ id: "x", name: 1 });
} catch (err: unknown) {
  console.log("expected fail:", err instanceof Error ? err.message : err);
}
