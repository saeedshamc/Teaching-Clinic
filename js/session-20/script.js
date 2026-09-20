/**
 * جلسه 20: تایمرها و Event Loop
 */

let tickId = null;
let ticks = 0;

function showOrder() {
    const lines = [];
    function log(msg) {
        lines.push(msg);
        document.getElementById('order-output').textContent =
            lines.join('\n') + '\n\nانتظار: A سپس D سپس C سپس B';
    }

    log('A همزمان');
    setTimeout(function () {
        log('B macrotask (timeout 0)');
    }, 0);
    Promise.resolve().then(function () {
        log('C microtask (Promise)');
    });
    log('D همزمان');
}

function startTicks() {
    stopTicks();
    ticks = 0;
    const output = document.getElementById('tick-output');
    tickId = setInterval(function () {
        ticks += 1;
        output.textContent = 'تیک ' + ticks;
        if (ticks >= 5) {
            stopTicks();
            output.textContent += '\nclearInterval بعد از 5';
        }
    }, 400);
}

function stopTicks() {
    if (tickId !== null) {
        clearInterval(tickId);
        tickId = null;
    }
}

window.addEventListener('DOMContentLoaded', function () {
    console.log('جلسه 20: Event Loop بارگذاری شد');
});
