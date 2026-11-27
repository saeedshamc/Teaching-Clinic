# پاسخ تمرین‌های جلسه ۲۹

## آسان

**۱.**
```java
class Pair<K, V> {
    K key;
    V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
}
Pair<String, Integer> p = new Pair<>("age", 20);
```

**۲.**
```java
static <T> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```

## متوسط

**۳.**
```java
static <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) > 0 ? a : b;
}
max(5, 3);       // Integer — Comparable
max("a", "b");   // String — Comparable
```

**۴.**
```java
static double sumWild(List<? extends Number> list) {
    double total = 0;
    for (Number n : list) total += n.doubleValue();
    return total;
}
```

## سخت

**۵.**
```java
class GenericRepository<T> {
    List<T> items = new ArrayList<>();
    void add(T item) { items.add(item); }
    T find(int index) { return items.get(index); }
    void delete(int index) { items.remove(index); }
}
```

**۶.**
```java
// ? super Integer یعنی Integer یا supertype (Number, Object)
// می‌توانیم Integer اضافه کنیم چون هر supertype هم Integer را می‌پذیرد
List<? super Integer> list = new ArrayList<Number>();
list.add(42);   // OK — Integer به Number اضافه می‌شود
// ولی نمی‌توانیم Number بخوانیم چون ممکن است Double باشد
```
