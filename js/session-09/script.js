/**
 * جلسه 9: رشته‌ها، Template Literal و Unicode پایه
 */

function showStringMethods() {
    const text = '  سلام JavaScript  ';
    let result = '';

    result += 'متن اصلی: "' + text + '"\n';
    result += 'length: ' + text.length + '\n';
    result += 'trim(): "' + text.trim() + '"\n';
    result += 'toUpperCase(): ' + text.trim().toUpperCase() + '\n';
    result += 'toLowerCase(): ' + text.trim().toLowerCase() + '\n';
    result += 'slice(0, 4): "' + text.trim().slice(0, 4) + '"\n';
    result += 'substring(5): "' + text.trim().substring(5) + '"\n';
    result += 'split(" "): ' + JSON.stringify(text.trim().split(' ')) + '\n';
    result += 'repeat(2): ' + 'ها'.repeat(2) + '\n';
    result += 'padStart(5, "0") روی "42": ' + '42'.padStart(5, '0') + '\n';

    document.getElementById('methods-output').textContent = result;
}

function showSearchReplace() {
    const sentence = 'علی در تهران زندگی می‌کند. علی برنامه‌نویس است.';
    let result = '';

    result += 'متن: ' + sentence + '\n\n';
    result += 'includes("تهران"): ' + sentence.includes('تهران') + '\n';
    result += 'startsWith("علی"): ' + sentence.startsWith('علی') + '\n';
    result += 'endsWith("است."): ' + sentence.endsWith('است.') + '\n';
    result += 'indexOf("علی"): ' + sentence.indexOf('علی') + '\n';
    result += 'lastIndexOf("علی"): ' + sentence.lastIndexOf('علی') + '\n';
    result += 'replace("علی", "محمد"): ' + sentence.replace('علی', 'محمد') + '\n';
    result += 'replaceAll("علی", "محمد"): ' + sentence.replaceAll('علی', 'محمد') + '\n';

    document.getElementById('search-output').textContent = result;
}

function showTemplates() {
    const product = { name: 'کیبورد', price: 850000, count: 2 };
    const total = product.price * product.count;

    const card = `
محصول: ${product.name}
تعداد: ${product.count}
قیمت واحد: ${product.price.toLocaleString('fa-IR')} تومان
جمع: ${total.toLocaleString('fa-IR')} تومان
وضعیت: ${total > 1000000 ? 'سفارش بزرگ' : 'سفارش عادی'}
`.trim();

    document.getElementById('template-output').textContent = card;
}

function showUnicode() {
    const word = 'سلام';
    const emoji = '👨‍💻';
    let result = '';

    result += 'کلمه: ' + word + '\n';
    result += 'length (واحد UTF-16): ' + word.length + '\n';
    result += '[...word].length (code point): ' + [...word].length + '\n';
    result += 'charCodeAt(0): ' + word.charCodeAt(0) + '\n';
    result += 'codePointAt(0): ' + word.codePointAt(0) + '\n\n';

    result += 'ایموجی: ' + emoji + '\n';
    result += 'emoji.length: ' + emoji.length + '  ← ممکن است بیش از 1 باشد\n';
    result += '[...emoji].length: ' + [...emoji].length + '\n';
    result += 'برای شمارش نویسه‌ها از [...str] یا Intl.Segmenter استفاده کنید.\n';

    document.getElementById('unicode-output').textContent = result;
}

window.addEventListener('DOMContentLoaded', function () {
    console.log('جلسه 9: رشته‌ها و Unicode بارگذاری شد');
});
