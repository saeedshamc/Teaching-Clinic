type Concrete<T> = { [K in keyof T]-?: T[K] };
type Soft = { a?: number };
type Hard = Concrete<Soft>;
const h: Hard = { a: 1 };
console.log(h);
