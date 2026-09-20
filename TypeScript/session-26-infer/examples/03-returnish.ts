type Ret<T> = T extends (...args: never[]) => infer R ? R : never;
type R = Ret<() => number>;
const r: R = 3;
console.log(r);
