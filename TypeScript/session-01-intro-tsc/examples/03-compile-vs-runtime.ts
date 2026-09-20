// جلسه ۱ — مرز کامپایل و runtime
// tsc نوع‌ها را قبل از اجرا چک می‌کند.
// Node بعد از پاک شدن نوع‌ها، همان JavaScript را اجرا می‌کند.

function double(n: number): number {
  return n * 2;
}

console.log("درست:", double(4));

// از نظر نوع قبول است؛ از نظر منطق ممکن است بی‌معنی باشد.
// NaN در JS از خانوادهٔ number است — کامپایلر دامنه را validate نمی‌کند.
console.log("نوع درست، منطق مشکوک:", double(Number.NaN));

const maybeText: string = "42";
const asNumber = Number(maybeText);
if (Number.isFinite(asNumber)) {
  console.log("تبدیل امن:", double(asNumber));
} else {
  console.log("داده برای double مناسب نیست");
}

// یادآوری: اگر داده از بیرون (API، فرم) بیاید، tsc آن را در runtime نگه نمی‌دارد.
// validate مال شماست؛ نوع مال قرارداد کد است.
