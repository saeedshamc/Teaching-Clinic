type Ev = `on${Capitalize<"click" | "focus">}`;
const e: Ev = "onClick";
console.log(e);
