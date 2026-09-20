type MyPartial<T> = { [K in keyof T]?: T[K] };
type User = { id: number; name: string };
const p: MyPartial<User> = { name: "x" };
console.log(p);
