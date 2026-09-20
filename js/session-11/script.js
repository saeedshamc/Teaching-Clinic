import { add, multiply, PI } from './math.js';
import greet, { version } from './greet.js';

function showMath() {
    const output = document.getElementById('math-output');
    let result = '';
    result += 'import { add, multiply, PI } from "./math.js";\n\n';
    result += 'add(12, 8) = ' + add(12, 8) + '\n';
    result += 'multiply(6, 7) = ' + multiply(6, 7) + '\n';
    result += 'PI = ' + PI + '\n';
    output.textContent = result;
}

function showGreet() {
    const output = document.getElementById('greet-output');
    let result = '';
    result += 'import greet, { version } from "./greet.js";\n\n';
    result += greet('سارا') + '\n';
    result += 'version: ' + version + '\n';
    output.textContent = result;
}

function showCombo() {
    const count = 3;
    const price = 150000;
    const total = multiply(count, price);
    const withTax = add(total, Math.round(total * 0.09));

    const output = document.getElementById('combo-output');
    output.textContent = [
        greet('خریدار'),
        'تعداد: ' + count,
        'جمع: ' + total,
        'با مالیات تقریبی: ' + withTax,
        'ماژول نسخه ' + version
    ].join('\n');
}

document.getElementById('btn-math').addEventListener('click', showMath);
document.getElementById('btn-greet').addEventListener('click', showGreet);
document.getElementById('btn-combo').addEventListener('click', showCombo);

console.log('جلسه 11: ماژول‌ها بارگذاری شد (type=module)');
