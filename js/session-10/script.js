/**
 * جلسه 10: کپی سطحی در برابر کپی عمیق
 */

function showReference() {
    let result = '';

    let a = 10;
    let b = a;
    b = 20;
    result += 'عدد (مقدار):\n';
    result += 'a = ' + a + '  (عوض نشد)\n';
    result += 'b = ' + b + '\n\n';

    const original = { name: 'علی', score: 18 };
    const alias = original;
    alias.score = 20;
    result += 'شیء (مرجع):\n';
    result += 'original.score = ' + original.score + '  (عوض شد!)\n';
    result += 'alias === original: ' + (alias === original) + '\n';

    document.getElementById('ref-output').textContent = result;
}

function showShallow() {
    const user = { name: 'سارا', city: 'تهران' };
    const copySpread = { ...user };
    const copyAssign = Object.assign({}, user);

    copySpread.city = 'اصفهان';

    let result = '';
    result += 'اصلی: ' + JSON.stringify(user) + '\n';
    result += 'کپی spread: ' + JSON.stringify(copySpread) + '\n';
    result += 'Object.assign: ' + JSON.stringify(copyAssign) + '\n';
    result += 'spread === اصلی؟ ' + (copySpread === user) + '\n';
    result += 'آرایه: const next = [...items]\n';

    document.getElementById('shallow-output').textContent = result;
}

function showNestedTrap() {
    const state = {
        user: { name: 'علی' },
        tags: ['js', 'dom']
    };

    const shallow = { ...state };
    shallow.user.name = 'محمد';
    shallow.tags.push('css');

    let result = '';
    result += 'بعد از کپی سطحی و تغییر تو در تو:\n';
    result += 'state.user.name = ' + state.user.name + '  ← اصل هم عوض شد\n';
    result += 'state.tags = ' + JSON.stringify(state.tags) + '\n';
    result += '\nspread فقط لایهٔ اول را جدا می‌کند.\n';

    document.getElementById('nested-output').textContent = result;
}

function showDeep() {
    const state = {
        user: { name: 'علی' },
        tags: ['js']
    };

    const deep = structuredClone(state);
    deep.user.name = 'رضا';
    deep.tags.push('node');

    const jsonCopy = JSON.parse(JSON.stringify(state));

    let result = '';
    result += 'اصلی بعد از structuredClone: ' + JSON.stringify(state) + '\n';
    result += 'کپی عمیق: ' + JSON.stringify(deep) + '\n';
    result += 'JSON.parse(JSON.stringify) هم برای دادهٔ ساده کار می‌کند\n';
    result += 'ولی Date، Map، undefined، function را درست کپی نمی‌کند.\n';
    result += '\nالگوی React: لایه به لایه کپی کنید:\n';
    result += 'const next = { ...state, user: { ...state.user, name: "رضا" } };\n';

    document.getElementById('deep-output').textContent = result;
    void jsonCopy;
}

window.addEventListener('DOMContentLoaded', function () {
    console.log('جلسه 10: کپی سطحی و عمیق بارگذاری شد');
});
