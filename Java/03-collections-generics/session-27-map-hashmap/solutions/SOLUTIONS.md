# پاسخ تمرین‌های جلسه ۲۷

## آسان

**۱.**
```java
Map<String, Integer> ages = new HashMap<>();
ages.put("Ali", 20);
ages.put("Sara", 22);
ages.put("Reza", 25);
System.out.println(ages.get("Ali"));   // 20
```

**۲.**
```java
Map<String, Integer> counts = new HashMap<>();
String[] words = {"hello", "world", "hello", "java"};
for (String w : words) {
    counts.put(w, counts.getOrDefault(w, 0) + 1);
}
// {hello=2, world=1, java=1}
```

## متوسط

**۳.**
```java
Map<String, Integer> linked = new LinkedHashMap<>();
linked.put("First", 1); linked.put("Second", 2); linked.put("Third", 3);
// ترتیب درج حفظ می‌شود
```

**۴.**
```java
Map<String, Integer> tree = new TreeMap<>();
tree.put("Zebra", 1); tree.put("Apple", 2); tree.put("Mango", 3);
// [Apple=2, Mango=3, Zebra=1] — مرتب بر اساس key
```

## سخت

**۵.**
```java
Map<String, List<Integer>> map = new HashMap<>();
map.computeIfAbsent("key", k -> new ArrayList<>()).add(1);
map.computeIfAbsent("key", k -> new ArrayList<>()).add(2);
// {key=[1, 2]}
```

**۶.**
```java
String text = "hello world hello java";
Map<String, Integer> freq = new HashMap<>();
for (String w : text.split(" ")) {
    freq.put(w, freq.getOrDefault(w, 0) + 1);
}
// {hello=2, world=1, java=1}
```
