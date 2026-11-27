# پاسخ تمرین‌های جلسه ۰۹

## آسان

**۱.**
```java
String s = "hello world";
s.toUpperCase();                    // بدون assign → تغییر نمی‌کند
System.out.println(s);              // hello world
s = s.toUpperCase();                // باید assign کرد
System.out.println(s);              // HELLO WORLD
```

**۲.**
```java
String s = "hello world";
String word = s.substring(s.indexOf("world"));   // "world"
System.out.println(word);
```

## متوسط

**۳.**
```java
String s = "hello world hello";
char c = 'l';
int count = 0, idx = 0;
while ((idx = s.indexOf(c, idx)) != -1) {
    count++;
    idx++;
}
System.out.println(count);   // 5
```

**۴.**
```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 10000; i++) sb.append("x");
String result = sb.toString();   // سریع
// concat با + در حلقهٔ بزرگ → O(n²) چون هر بار String جدید می‌سازد
```

## سخت

**۵.**
```java
String s = "Ali Rezaei";
StringBuilder words = new StringBuilder();
for (int i = 0; i < s.length(); i++) {
    char ch = s.charAt(i);
    if (ch == ' ') {
        System.out.println(words);   // کلمهٔ فعلی
        words.setLength(0);
    } else {
        words.append(ch);
    }
}
System.out.println(words);   // کلمهٔ آخر
```

**۶.**
```java
static boolean isPalindrome(String s) {
    String rev = new StringBuilder(s).reverse().toString();
    return s.equalsIgnoreCase(rev);
}
// یا بدون StringBuilder:
static boolean isPalindrome2(String s) {
    int i = 0, j = s.length() - 1;
    while (i < j) {
        if (s.charAt(i) != s.charAt(j)) return false;
        i++; j--;
    }
    return true;
}
```
