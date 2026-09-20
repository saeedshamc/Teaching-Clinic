type User = { id: number; name: string };
type Flags<T> = { [K in keyof T]: boolean };
const f: Flags<User> = { id: true, name: false };
console.log(f);
