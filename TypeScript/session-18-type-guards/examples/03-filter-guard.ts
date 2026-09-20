const mixed: (string | number)[] = ["a", 1, "b", 2];
const nums = mixed.filter((x): x is number => typeof x === "number");
console.log(nums);
