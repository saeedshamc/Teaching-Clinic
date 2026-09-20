/**
 * جلسه 18: فرم‌ها، اعتبارسنجی و FormData
 */

window.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('signup-form');
    const output = document.getElementById('form-output');
    const nameInput = form.elements.name;

    nameInput.addEventListener('input', function () {
        nameInput.setCustomValidity('');
    });

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        if (nameInput.value.trim().length < 2) {
            nameInput.setCustomValidity('نام را کامل بنویسید');
        } else {
            nameInput.setCustomValidity('');
        }

        if (!form.checkValidity()) {
            form.reportValidity();
            output.textContent = 'مرورگر اعتبارسنجی را رد کرد.';
            return;
        }

        const data = new FormData(form);
        const payload = Object.fromEntries(data.entries());
        payload.terms = data.get('terms') === 'yes';

        output.textContent =
            'preventDefault جلوی رفرش را گرفت.\n' +
            JSON.stringify(payload, null, 2) +
            '\n\ncheckbox بدون تیک در FormData نمیآید.';
    });

    console.log('جلسه 18: فرم‌ها بارگذاری شد');
});
