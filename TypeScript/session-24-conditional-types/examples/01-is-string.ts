type IsString<T> = T extends string ? true : false;
type A = IsString<"a">;
type B = IsString<1>;
const a: A = true;
const b: B = false;
console.log(a, b);
