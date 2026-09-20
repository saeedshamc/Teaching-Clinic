type NumTree = { value: number; children: NumTree[] };
function sum(t: NumTree): number {
  return t.value + t.children.reduce((a, c) => a + sum(c), 0);
}
console.log(sum({ value: 1, children: [{ value: 2, children: [] }] }));
