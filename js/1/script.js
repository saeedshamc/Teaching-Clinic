/**
 * برنامه محاسبه فاکتوریل
 * از کاربر عدد دریافت می‌کند و فاکتوریل آن را در کنسول چاپ می‌کند
 */

// تابع محاسبه فاکتوریل
function factorial(n) {
    if (n < 0) {
        throw new Error('فاکتوریل برای اعداد منفی تعریف نشده است!');
    }
    if (n === 0 || n === 1) {
        return 1;
    }
    return n * factorial(n - 1);
}

// دریافت عدد از کاربر با prompt
const input = prompt('لطفاً یک عدد وارد کنید:');

// بررسی اینکه کاربر Cancel نکرده باشد

    // تبدیل ورودی به عدد
    const number = parseInt(input, 10);
    
// بررسی معتبر بودن ورودی
    if (isNaN(number)) {
        console.log('❌ خطا: لطفاً یک عدد معتبر وارد کنید!');
    } else if (number < 0) {
        console.log('❌ خطا: فاکتوریل برای اعداد منفی تعریف نشده است!');
    } else {
        // محاسبه و چاپ فاکتوریل در کنسول
        const result = factorial(number);
        console.log(`فاکتوریل ${number} برابر است با: ${result}`);
    }

