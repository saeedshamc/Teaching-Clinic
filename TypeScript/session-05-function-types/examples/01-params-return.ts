function add(a: number, b: number): number {
  return a + b;
}

function label(id: number): string {
  return `#${id}`;
}

// برگشت با inference
function area(width: number, height: number) {
  return width * height;
}

console.log("add:", add(2, 3));
console.log("label:", label(42));
console.log("area:", area(4, 5));
