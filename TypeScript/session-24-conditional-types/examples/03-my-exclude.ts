type MyExclude<T, U> = T extends U ? never : T;
type T = MyExclude<"a" | "b" | "c", "a">;
const x: T = "b";
console.log(x);
