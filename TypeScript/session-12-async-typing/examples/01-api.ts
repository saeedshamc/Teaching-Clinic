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

async function fetchUser(id: number): Promise<UserDto> {
  const res = await fetch(`https://jsonplaceholder.typicode.com/users/${id}`);
  if (!res.ok) {
    throw new Error(`HTTP ${res.status}`);
  }
  const data: unknown = await res.json();
  return parseUser(data);
}

async function main(): Promise<void> {
  const local = parseUser({ id: 1, name: "محلی" });
  console.log("local:", local);

  try {
    const remote = await fetchUser(1);
    console.log("remote:", remote);
  } catch (err: unknown) {
    const message = err instanceof Error ? err.message : String(err);
    console.log("fetch skipped/failed:", message);
  }
}

void main();
