// این فایل سالم است — برای دیدن شکل کد درست.
// عمداً خطا را در کپی شخصی‌تان بسازید، نه اینجا.

function add(a: number, b: number): number {
  return a + b;
}

const total: number = add(2, 3);
console.log("total:", total);
console.log("اگر string به number بدهید، TS2322/TS2345 می‌بینید.");
