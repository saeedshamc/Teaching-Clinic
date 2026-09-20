export type User = { id: number; name: string };
export function getUserStub(): User {
  return { id: 1, name: "سارا" };
}
console.log(getUserStub());
