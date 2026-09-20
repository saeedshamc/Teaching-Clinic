export type User = { id: number; name: string };
export function label(u: User): string { return u.name; }
console.log(label({ id: 1, name: "n" }));
