export type PublicUser = { id: number; name: string };
export function label(u: PublicUser): string {
  return u.name;
}
console.log(label({ id: 1, name: "سارا" }));
