type Mapper = { (n: number): number };
const double: Mapper = (n) => n * 2;
console.log(double(4));
