type Unwrap<T> = T extends Promise<infer U> ? U : T;
type S = Unwrap<Promise<string>>;
const s: S = "ok";
console.log(s);
