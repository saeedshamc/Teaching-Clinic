function identity<T>(x: T): T { return x; }
const n = identity<number>(3);
console.log(n);
