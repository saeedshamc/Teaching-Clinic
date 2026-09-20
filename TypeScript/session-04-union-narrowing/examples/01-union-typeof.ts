// جلسه ۴ — union و typeof
function showId(id: string | number): string {
  if (typeof id === "string") {
    return id.trim().toUpperCase();
  }
  return `#${id.toFixed(0)}`;
}

function lengthOf(text: string | null): number {
  if (text === null) {
    return 0;
  }
  return text.length;
}

console.log(showId("  ab "));
console.log(showId(7));
console.log(lengthOf(null), lengthOf("TS"));
