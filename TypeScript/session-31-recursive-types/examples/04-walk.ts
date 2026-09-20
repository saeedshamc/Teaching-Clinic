type Tree = { value: string; children: Tree[] };
function walk(t: Tree, out: string[] = []): string[] {
  out.push(t.value);
  for (const c of t.children) walk(c, out);
  return out;
}
console.log(walk({ value: "a", children: [{ value: "b", children: [] }] }));
