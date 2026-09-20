type ToArray<T> = T extends unknown ? T[] : never;
type T = ToArray<string | number>;
const sample: T = ["a"];
console.log(sample);
