type Elem<T> = T extends (infer E)[] ? E : never;
type N = Elem<number[]>;
const n: N = 1;
console.log(n);
