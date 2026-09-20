// جلسه ۱ — خواندن خطای کامپایلر
// خطوط ❌ را یکی‌یکی از کامنت درآورید، پیام tsc را بخوانید، دوباره کامنت کنید.

const score: number = 18;
console.log("نمره:", score);

// ❌ TS2322: Type 'string' is not assignable to type 'number'.
// const brokenScore: number = "هجده";

function add(a: number, b: number): number {
  return a + b;
}

console.log("جمع:", add(10, 5));

// ❌ TS2345: Argument of type 'string' is not assignable to parameter of type 'number'.
// add("10", 5);

// عادت: دو طرف نوع را بخوانید (چپ/راست یا پارامتر/آرگومان)، بعد کد را عوض کنید.
// ضدعادت: any یا @ts-ignore.
