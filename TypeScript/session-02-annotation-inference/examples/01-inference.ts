// جلسه ۲ — inference
let title = "یادداشت روزانه";
let pages = 3;
let published = false;

title = "یادداشت ویرایش‌شده";
pages = 4;
published = true;

const course = "TypeScript"; // عوض نمی‌شود؛ inference دقیق‌تر است

console.log(title, pages, published, course);

function square(n: number) {
  return n * n; // برگشت number حدس زده می‌شود
}

console.log("مربع:", square(5));
