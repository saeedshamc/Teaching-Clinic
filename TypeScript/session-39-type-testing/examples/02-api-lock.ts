type Public = { id: number; name: string };
function expectType<T>(_v: T): void {}
expectType<Public>({ id: 1, name: "x" });
console.log("public shape locked");
