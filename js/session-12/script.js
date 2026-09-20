/**
 * جلسه 12: کلاس‌ها و پروتوتایپ
 */

class User {
    constructor(name) {
        this.name = name;
    }

    greet() {
        return 'سلام ' + this.name;
    }
}

class Admin extends User {
    constructor(name, role) {
        super(name);
        this.role = role;
    }

    greet() {
        return super.greet() + ' — نقش: ' + this.role;
    }
}

function showClass() {
    const user = new User('سارا');
    let result = '';
    result += 'const user = new User("سارا");\n';
    result += user.greet() + '\n';
    result += 'user instanceof User: ' + (user instanceof User) + '\n';
    document.getElementById('class-output').textContent = result;
}

function showPrototype() {
    const user = new User('علی');
    let result = '';
    result += 'user.hasOwnProperty("name"): ' + user.hasOwnProperty('name') + '\n';
    result += 'user.hasOwnProperty("greet"): ' + user.hasOwnProperty('greet') + '\n';
    result += 'متد greet روی User.prototype است\n';
    result += 'user.greet === User.prototype.greet: ' + (user.greet === User.prototype.greet) + '\n';
    result += 'Object.getPrototypeOf(user) === User.prototype: ';
    result += (Object.getPrototypeOf(user) === User.prototype) + '\n';
    document.getElementById('proto-output').textContent = result;
}

function showExtends() {
    const admin = new Admin('رضا', 'مدیر');
    let result = '';
    result += admin.greet() + '\n';
    result += 'admin instanceof Admin: ' + (admin instanceof Admin) + '\n';
    result += 'admin instanceof User: ' + (admin instanceof User) + '\n';
    result += 'super(name) در constructor فرزند قبل از this لازم است.\n';
    document.getElementById('extends-output').textContent = result;
}

window.addEventListener('DOMContentLoaded', function () {
    console.log('جلسه 12: کلاس‌ها و پروتوتایپ بارگذاری شد');
});
