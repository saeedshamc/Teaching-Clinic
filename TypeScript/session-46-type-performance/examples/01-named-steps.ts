type Raw = { a: number };
type Step1 = Raw & { b: string };
type Final = Step1 & { c: boolean };
const v: Final = { a: 1, b: "x", c: true };
console.log(v);
