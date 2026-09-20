/**
 * جلسه 21: Set، Map و Date
 */

function showSet() {
    const tags = ['js', 'dom', 'js', 'css', 'dom'];
    const unique = [...new Set(tags)];
    document.getElementById('set-output').textContent =
        'ورودی: ' + JSON.stringify(tags) + '\n' +
        'Set: ' + JSON.stringify(unique) + '\n' +
        'size: ' + new Set(tags).size;
}

function showMap() {
    const scores = new Map([
        ['سارا', 19],
        ['علی', 16]
    ]);
    scores.set('رضا', 18);
    let result = '';
    for (const [name, score] of scores) {
        result += name + ': ' + score + '\n';
    }
    result += 'get("علی") = ' + scores.get('علی') + '\n';
    result += 'has("مریم") = ' + scores.has('مریم');
    document.getElementById('map-output').textContent = result;
}

function showIntl() {
    const now = new Date();
    const date = new Intl.DateTimeFormat('fa-IR', {
        dateStyle: 'full',
        timeStyle: 'short'
    }).format(now);
    const money = new Intl.NumberFormat('fa-IR').format(1250000);
    document.getElementById('intl-output').textContent =
        'ISO: ' + now.toISOString() + '\n' +
        'fa-IR: ' + date + '\n' +
        'عدد: ' + money + ' تومان';
}

window.addEventListener('DOMContentLoaded', function () {
    console.log('جلسه 21: Set/Map/Date بارگذاری شد');
});
