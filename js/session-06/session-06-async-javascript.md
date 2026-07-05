# جلسه 6: JavaScript ناهمگام (Asynchronous) (2 ساعت)

## 🎯 اهداف یادگیری
- درک Asynchronous Programming
- کار با Callbacks
- استفاده از Promises
- Async/Await
- Fetch API

---

## 1. Synchronous vs Asynchronous

### 1.1. Synchronous (همگام)
```javascript
console.log("1");
console.log("2");
console.log("3");
// خروجی: 1, 2, 3 (به ترتیب)
```

### 1.2. Asynchronous (ناهمگام)
```javascript
console.log("1");

setTimeout(() => {
    console.log("2");
}, 1000);

console.log("3");
// خروجی: 1, 3, 2 (2 بعد از 1 ثانیه)
```

**چرا Asynchronous مهم است؟**
- درخواست‌های شبکه (API)
- خواندن فایل‌ها
- عملیات‌های زمان‌بر
- بدون blocking کردن UI

---

## 2. Callbacks (قدیمی - کمتر استفاده)

### 2.1. Callback ساده
```javascript
function fetchData(callback) {
    setTimeout(() => {
        callback("داده دریافت شد");
    }, 1000);
}

fetchData(function(data) {
    console.log(data);
});
```

### 2.2. Callback Hell (مشکل)
```javascript
// مشکل: Callback Hell
getData(function(a) {
    getMoreData(a, function(b) {
        getMoreData(b, function(c) {
            getMoreData(c, function(d) {
                // 😱 خیلی عمیق شد!
                console.log(d);
            });
        });
    });
});
```

---

## 3. Promises

Promise یک object است که نشان‌دهنده موفقیت یا شکست یک عملیات ناهمگام است.

### 3.1. ساخت Promise
```javascript
let promise = new Promise(function(resolve, reject) {
    // عملیات ناهمگام
    setTimeout(() => {
        let success = true;
        if (success) {
            resolve("موفق شد!");
        } else {
            reject("خطا!");
        }
    }, 1000);
});
```

### 3.2. استفاده از Promise
```javascript
promise
    .then(function(result) {
        console.log(result); // "موفق شد!"
    })
    .catch(function(error) {
        console.log(error); // "خطا!"
    });
```

### 3.3. مثال عملی
```javascript
function fetchUser(userId) {
    return new Promise(function(resolve, reject) {
        setTimeout(() => {
            if (userId > 0) {
                resolve({ id: userId, name: "علی" });
            } else {
                reject("شناسه کاربر نامعتبر");
            }
        }, 1000);
    });
}

fetchUser(1)
    .then(function(user) {
        console.log("کاربر:", user);
    })
    .catch(function(error) {
        console.log("خطا:", error);
    });
```

### 3.4. Chaining Promises
```javascript
fetchUser(1)
    .then(function(user) {
        console.log("کاربر:", user);
        return fetchUserPosts(user.id);
    })
    .then(function(posts) {
        console.log("پست‌ها:", posts);
        return fetchUserComments(posts[0].id);
    })
    .then(function(comments) {
        console.log("نظرات:", comments);
    })
    .catch(function(error) {
        console.log("خطا:", error);
    });
```

### 3.5. Promise.all()
```javascript
// اجرای چند Promise به صورت موازی
let promise1 = fetchUser(1);
let promise2 = fetchUser(2);
let promise3 = fetchUser(3);

Promise.all([promise1, promise2, promise3])
    .then(function(users) {
        console.log("همه کاربران:", users);
    })
    .catch(function(error) {
        console.log("خطا:", error);
    });
```

### 3.6. Promise.race()
```javascript
// اولین Promise که resolve شود
Promise.race([promise1, promise2, promise3])
    .then(function(user) {
        console.log("اولین کاربر:", user);
    });
```

### 3.7. Promise.allSettled() (ES2020)
```javascript
// منتظر می‌ماند تا همه Promiseها تمام شوند (موفق یا ناموفق)
Promise.allSettled([promise1, promise2, promise3])
    .then(function(results) {
        results.forEach((result, index) => {
            if (result.status === "fulfilled") {
                console.log(`Promise ${index}:`, result.value);
            } else {
                console.log(`Promise ${index}:`, result.reason);
            }
        });
    });
```

---

## 4. Async/Await (پیشنهادی)

Async/Await یک syntax بهتر برای کار با Promises است.

### 4.1. تعریف Async Function
```javascript
async function fetchData() {
    // این تابع همیشه یک Promise برمی‌گرداند
    return "داده";
}

// معادل:
function fetchData() {
    return Promise.resolve("داده");
}
```

### 4.2. استفاده از Await
```javascript
async function getUser() {
    try {
        let user = await fetchUser(1);
        console.log("کاربر:", user);
    } catch (error) {
        console.log("خطا:", error);
    }
}

getUser();
```

### 4.3. Chaining با Async/Await
```javascript
async function getUserData() {
    try {
        let user = await fetchUser(1);
        console.log("کاربر:", user);
        
        let posts = await fetchUserPosts(user.id);
        console.log("پست‌ها:", posts);
        
        let comments = await fetchUserComments(posts[0].id);
        console.log("نظرات:", comments);
    } catch (error) {
        console.log("خطا:", error);
    }
}
```

### 4.4. اجرای موازی با Async/Await
```javascript
async function getMultipleUsers() {
    try {
        // اجرای موازی
        let [user1, user2, user3] = await Promise.all([
            fetchUser(1),
            fetchUser(2),
            fetchUser(3)
        ]);
        
        console.log("کاربران:", user1, user2, user3);
    } catch (error) {
        console.log("خطا:", error);
    }
}
```

### 4.5. مقایسه Promise و Async/Await
```javascript
// با Promise
fetchUser(1)
    .then(user => {
        return fetchUserPosts(user.id);
    })
    .then(posts => {
        console.log(posts);
    })
    .catch(error => {
        console.log(error);
    });

// با Async/Await (خوانا‌تر)
async function getPosts() {
    try {
        let user = await fetchUser(1);
        let posts = await fetchUserPosts(user.id);
        console.log(posts);
    } catch (error) {
        console.log(error);
    }
}
```

---

## 5. Fetch API

Fetch API برای درخواست‌های HTTP استفاده می‌شود.

### 5.1. GET Request
```javascript
fetch('https://api.example.com/users')
    .then(response => {
        if (!response.ok) {
            throw new Error('خطا در دریافت داده');
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
    })
    .catch(error => {
        console.log('خطا:', error);
    });
```

### 5.2. با Async/Await
```javascript
async function getUsers() {
    try {
        let response = await fetch('https://api.example.com/users');
        
        if (!response.ok) {
            throw new Error('خطا در دریافت داده');
        }
        
        let data = await response.json();
        console.log(data);
    } catch (error) {
        console.log('خطا:', error);
    }
}
```

### 5.3. POST Request
```javascript
async function createUser(userData) {
    try {
        let response = await fetch('https://api.example.com/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });
        
        if (!response.ok) {
            throw new Error('خطا در ایجاد کاربر');
        }
        
        let data = await response.json();
        return data;
    } catch (error) {
        console.log('خطا:', error);
    }
}

createUser({ name: "علی", age: 25 });
```

### 5.4. PUT Request
```javascript
async function updateUser(userId, userData) {
    try {
        let response = await fetch(`https://api.example.com/users/${userId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });
        
        if (!response.ok) {
            throw new Error('خطا در به‌روزرسانی');
        }
        
        return await response.json();
    } catch (error) {
        console.log('خطا:', error);
    }
}
```

### 5.5. DELETE Request
```javascript
async function deleteUser(userId) {
    try {
        let response = await fetch(`https://api.example.com/users/${userId}`, {
            method: 'DELETE'
        });
        
        if (!response.ok) {
            throw new Error('خطا در حذف');
        }
        
        return true;
    } catch (error) {
        console.log('خطا:', error);
        return false;
    }
}
```

### 5.6. مدیریت خطا در Fetch
```javascript
async function fetchWithErrorHandling(url) {
    try {
        let response = await fetch(url);
        
        // بررسی status code
        if (response.status === 404) {
            throw new Error('یافت نشد');
        }
        
        if (response.status === 500) {
            throw new Error('خطای سرور');
        }
        
        if (!response.ok) {
            throw new Error(`خطا: ${response.status}`);
        }
        
        return await response.json();
    } catch (error) {
        if (error.name === 'TypeError') {
            console.log('مشکل شبکه');
        } else {
            console.log('خطا:', error.message);
        }
        throw error;
    }
}
```

---

## 6. مثال کامل: API Service

```javascript
class ApiService {
    constructor(baseURL) {
        this.baseURL = baseURL;
    }
    
    async get(endpoint) {
        try {
            let response = await fetch(`${this.baseURL}${endpoint}`);
            if (!response.ok) throw new Error('خطا در دریافت');
            return await response.json();
        } catch (error) {
            console.error('GET Error:', error);
            throw error;
        }
    }
    
    async post(endpoint, data) {
        try {
            let response = await fetch(`${this.baseURL}${endpoint}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });
            if (!response.ok) throw new Error('خطا در ارسال');
            return await response.json();
        } catch (error) {
            console.error('POST Error:', error);
            throw error;
        }
    }
    
    async put(endpoint, data) {
        try {
            let response = await fetch(`${this.baseURL}${endpoint}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });
            if (!response.ok) throw new Error('خطا در به‌روزرسانی');
            return await response.json();
        } catch (error) {
            console.error('PUT Error:', error);
            throw error;
        }
    }
    
    async delete(endpoint) {
        try {
            let response = await fetch(`${this.baseURL}${endpoint}`, {
                method: 'DELETE'
            });
            if (!response.ok) throw new Error('خطا در حذف');
            return true;
        } catch (error) {
            console.error('DELETE Error:', error);
            throw error;
        }
    }
}

// استفاده
const api = new ApiService('https://api.example.com');

async function main() {
    try {
        // دریافت کاربران
        let users = await api.get('/users');
        console.log(users);
        
        // ایجاد کاربر
        let newUser = await api.post('/users', {
            name: "علی",
            age: 25
        });
        console.log(newUser);
        
        // به‌روزرسانی
        await api.put(`/users/${newUser.id}`, {
            name: "علی محمدی"
        });
        
        // حذف
        await api.delete(`/users/${newUser.id}`);
    } catch (error) {
        console.log('خطا:', error);
    }
}
```

---

## 7. تمرین‌ها

### تمرین 1: Promise
```javascript
// تابعی بنویسید که یک Promise می‌سازد:
// - اگر عدد مثبت باشد، resolve می‌کند
// - اگر منفی باشد، reject می‌کند
// سپس از آن استفاده کنید
```

### تمرین 2: Async/Await
```javascript
// با استفاده از Async/Await:
// 1. یک کاربر را از API دریافت کنید
// 2. پست‌های او را دریافت کنید
// 3. نظرات اولین پست را دریافت کنید
// همه را با try/catch مدیریت کنید
```

### تمرین 3: Fetch
```javascript
// یک تابع بنویسید که:
// - از API کاربران را دریافت می‌کند
// - فقط کاربران بالای 18 سال را فیلتر می‌کند
// - نام آن‌ها را در یک آرایه برمی‌گرداند
```

---

## 8. نکات مهم

1. ✅ از Async/Await به جای Callbacks استفاده کنید
2. ✅ همیشه خطاها را با try/catch مدیریت کنید
3. ✅ از Promise.all برای اجرای موازی استفاده کنید
4. ✅ در Fetch همیشه response.ok را بررسی کنید
5. ✅ از JSON.stringify برای ارسال داده استفاده کنید
6. ❌ از Callback Hell اجتناب کنید

---

## 9. خلاصه جلسه

در این جلسه یاد گرفتیم:
- ✅ Synchronous vs Asynchronous
- ✅ Promises و استفاده از آن‌ها
- ✅ Async/Await (پیشنهادی)
- ✅ Fetch API برای HTTP Requests
- ✅ مدیریت خطا در عملیات ناهمگام

**این مفاهیم برای کار با API در React ضروری هستند! 🚀**

