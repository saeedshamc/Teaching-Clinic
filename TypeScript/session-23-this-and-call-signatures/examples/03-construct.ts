type Box = { value: number };
type BoxCtor = { new (value: number): Box };
class BoxImpl {
  constructor(public value: number) {}
}
const Ctor: BoxCtor = BoxImpl;
console.log(new Ctor(10).value);
