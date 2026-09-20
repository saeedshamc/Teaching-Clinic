type User = { id: number };
export type { User };
export function idOf(u: User): number { return u.id; }
console.log(idOf({ id: 1 }));
