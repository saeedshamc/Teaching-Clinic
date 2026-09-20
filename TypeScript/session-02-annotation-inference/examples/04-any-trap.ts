// جلسه ۲ — دام any (عمداً از any استفاده نمی‌کنیم)
function wrapCount(count: number): number {
  return count;
}

const y = wrapCount(4);
console.log("خروجی هنوز number است:", y + 1);

// اگر wrapCount(x: any) بود، y هم any می‌شد و y.foo خطا نمی‌گرفت.
// any را ننویسید؛ نوع واقعی را بنویسید.
