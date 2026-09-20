type Kind = "asc" | "desc";
function createSorter(kind: Kind): (nums: number[]) => number[] {
  if (kind === "asc") return (n) => [...n].sort((a, b) => a - b);
  return (n) => [...n].sort((a, b) => b - a);
}
console.log(createSorter("desc")([1, 2, 3]));
