type MyReadonly<T> = { readonly [K in keyof T]: T[K] };
type Point = { x: number; y: number };
const o: MyReadonly<Point> = { x: 0, y: 0 };
console.log(o.x);
