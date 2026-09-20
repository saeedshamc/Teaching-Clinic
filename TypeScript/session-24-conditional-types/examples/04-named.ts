type NonString<T> = T extends string ? never : T;
type T = NonString<string | number | boolean>;
const n: T = 1;
console.log(n);
