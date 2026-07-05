// تابع برای نمایش جدول ضرب کامل
function multiplicationTable(n) {
    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= n; j++) {
            console.log(`${i} × ${j} = ${i * j}`);
        }
    }
}

// استفاده
multiplicationTable(3);






///////////////////////////////////////////////////
// تابع برای نمایش جدول ضرب کامل
function multiplicationTable(n) {
    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= n; j++) {
            console.log(`${i} × ${j} = ${i * j}`);
        }
    }
}

// دریافت عدد از کاربر
const userInput = prompt("لطفاً یک عدد وارد کنید:");
const n = parseInt(userInput);

// بررسی معتبر بودن عدد
if (isNaN(n) || n <= 0) {
    console.log("لطفاً یک عدد معتبر و بزرگ‌تر از صفر وارد کنید!");
} else {
    console.log(`جدول ضرب ${n}:`);
    console.log("-------------------");
    multiplicationTable(n);
}