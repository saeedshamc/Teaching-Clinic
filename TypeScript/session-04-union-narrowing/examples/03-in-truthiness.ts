// جلسه ۴ — truthiness و in
type Admin = { name: string; role: "admin" };
type Guest = { name: string; visitor: true };

function greet(person: Admin | Guest): string {
  if ("role" in person) {
    return `ادمین ${person.name}`;
  }
  return `مهمان ${person.name}`;
}

function len(text: string | null | undefined): number {
  if (!text) {
    return 0;
  }
  return text.length;
}

console.log(greet({ name: "رضا", role: "admin" }));
console.log(greet({ name: "سارا", visitor: true }));
console.log(len(null), len("TS"));
