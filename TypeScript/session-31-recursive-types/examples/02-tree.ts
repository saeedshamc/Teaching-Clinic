type Tree = { value: string; children: Tree[] };
const t: Tree = { value: "root", children: [{ value: "c", children: [] }] };
console.log(t.children[0]?.value);
