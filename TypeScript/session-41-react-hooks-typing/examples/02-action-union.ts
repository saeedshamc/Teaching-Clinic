type Action =
  | { type: "inc" }
  | { type: "dec" }
  | { type: "reset" };
function reduce(count: number, action: Action): number {
  switch (action.type) {
    case "inc": return count + 1;
    case "dec": return count - 1;
    case "reset": return 0;
  }
}
console.log(reduce(1, { type: "inc" }));
