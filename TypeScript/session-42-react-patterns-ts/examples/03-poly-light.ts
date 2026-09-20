type Clickable = { as?: "button" | "a"; label: string };
function describe(c: Clickable): string {
  return `${c.as ?? "button"}:${c.label}`;
}
console.log(describe({ label: "Go", as: "a" }));
