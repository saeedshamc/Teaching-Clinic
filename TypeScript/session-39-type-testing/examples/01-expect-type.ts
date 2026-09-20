function expectType<T>(_value: T): void {}
expectType<number>(1);
expectType<string>("a");
console.log("type assertions compiled");
