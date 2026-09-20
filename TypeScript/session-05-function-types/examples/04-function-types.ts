type Mapper = (value: number) => number;
type Predicate = (value: number) => boolean;

const double: Mapper = (n) => n * 2;
const isEven: Predicate = (n) => n % 2 === 0;

function applyAll(nums: number[], map: Mapper): number[] {
  return nums.map(map);
}

function countWhere(nums: number[], pred: Predicate): number {
  return nums.filter(pred).length;
}

const data = [1, 2, 3, 4, 5];
console.log("doubled:", applyAll(data, double));
console.log("even count:", countWhere(data, isEven));
