import { parseUser } from "./01-parse-local.js";

async function fetchUser(id: number): Promise<{ id: number; name: string }> {
  const res = await fetch(`https://jsonplaceholder.typicode.com/users/${id}`);
  if (!res.ok) {
    throw new Error(`HTTP ${res.status}`);
  }
  const data: unknown = await res.json();
  return parseUser(data);
}

async function main(): Promise<void> {
  try {
    const remote = await fetchUser(1);
    console.log("remote:", remote);
  } catch (err: unknown) {
    const message = err instanceof Error ? err.message : String(err);
    console.log("fetch skipped/failed:", message);
  }
}

void main();
