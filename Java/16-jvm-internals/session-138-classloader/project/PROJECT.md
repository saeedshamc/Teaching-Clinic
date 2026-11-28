# پروژهٔ جلسه ۱۳۸ — تحلیل ClassLoader

یک پروژهٔ تحلیل ClassLoader بسازید:
- `ClassLoaderDemo` با `getClassLoader()`, `getParent()`, `Class.forName()`
- `CustomClassLoader` با `findClass()` برای custom loading
- `ReflectionDemo` با `getDeclaredConstructors()`, `getDeclaredMethods()`
- `ResourceLoader` با `getResource()`, `getResourceAsStream()`

نمونه:
```
ClassLoader: jdk.internal.loader.ClassLoaders$AppClassLoader
Parent: jdk.internal.loader.ClassLoaders$PlatformClassLoader
Bootstrap ClassLoader: null
Loaded class: java.lang.String
Resource loaded: true
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
