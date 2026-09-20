function labelOf<T extends { label: string }>(item: T): string {
  return item.label;
}

function pluck<T, K extends keyof T>(obj: T, key: K): T[K] {
  return obj[key];
}

const tagged = { label: "urgent", id: 9 };
console.log(labelOf(tagged));
console.log(pluck({ name: "سارا", age: 20 }, "name"));
console.log(pluck({ name: "سارا", age: 20 }, "age"));
