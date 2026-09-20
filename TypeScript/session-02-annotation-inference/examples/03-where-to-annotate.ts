// جلسه ۲ — کجا annotation بنویسید
function parseScore(raw: string): number {
  return Number(raw);
}

let pending: number;
pending = parseScore("18");

const obvious = "این :string نمی‌خواهد";

console.log(pending, obvious);

// پارامتر بدون نوع در strict خطا است:
// function bad(x) { return x; }
