// جلسه ۱ — چک‌لیست گردش کار
// 1) بنویس  2) خطای ادیتور را بخوان  3) npx tsc --noEmit  4) در صورت نیاز npx tsx

const steps: string[] = ["write", "read-error", "typecheck", "run"];

for (const step of steps) {
  console.log("گام:", step);
}

console.log("نوع‌ها در runtime نیستند؛ بعد از transpile همین رشته‌ها باقی می‌مانند.");
