// جلسه ۱ — اولین برنامهٔ TypeScript
// اجرا: از ریشهٔ TypeScript/
//   npx tsx session-01-intro-tsc/examples/01-hello.ts

function greet(name: string): string {
  return `سلام، ${name}`;
}

const student: string = "سارا";
console.log(greet(student));

const year: number = 2026;
console.log("سال دوره:", year);

// قرارداد نوع یعنی خواننده نمی‌پرسد name چیست.
// اگر به‌جای رشته عدد بدهید، tsc قبل از اجرا اعتراض می‌کند.
