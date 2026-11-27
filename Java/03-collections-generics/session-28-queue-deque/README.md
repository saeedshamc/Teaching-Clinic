# جلسه ۲۸ — Queue و Deque

## اهداف یادگیری
- `Queue` و `Deque` را به‌کار ببرید
- FIFO در برابر LIFO را بفهمید
- `PriorityQueue` برای sorted queue را بشناسید
- `ArrayDeque` در برابر `LinkedList` را تشخیص دهید

## پیش‌نیازها
- جلسات ۲۵–۲۷

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### Queue — FIFO (First In, First Out)

```java
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayDeque;

Queue<String> queue = new LinkedList<>();   // یا ArrayDeque
queue.offer("First");
queue.offer("Second");
queue.offer("Third");

System.out.println(queue.poll());   // First — اول خارج می‌شود
System.out.println(queue.peek());   // Second — نگاه بدون حذف
System.out.println(queue);          // [Third]
```

### Deque — Double Ended Queue

```java
import java.util.Deque;
import java.util.ArrayDeque;

Deque<String> deque = new ArrayDeque<>();
deque.offerFirst("Front");
deque.offerLast("Back");
System.out.println(deque.pollFirst());   // Front
deque.offer("Middle");                   // به آخر (مثل offerLast)
System.out.println(deque);               // [Back, Middle]
```

### PriorityQueue — sorted

```java
import java.util.PriorityQueue;

PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.offer(5);
pq.offer(1);
pq.offer(3);
pq.offer(2);
System.out.println(pq.poll());   // 1 — کوچک‌ترین اول
System.out.println(pq.poll());   // 2
System.out.println(pq);          // [3, 5]
```
- PriorityQueue بر اساس natural ordering مرتب می‌کند — نه ترتیب درج.

### ArrayDeque در برابر LinkedList

| ArrayDeque | LinkedList |
|---|---|
| پیاده‌سازی روی array | پیاده‌سازی روی linked list |
| سریع‌تر برای queue/deque | انعطاف‌پذیرتر (List هم هست) |
| null نمی‌پذیرد | null می‌پذیرد |
| پیش‌فرض برای stack/queue | برای List استفاده می‌شود |

## کد مهم

`src/QueueDequeDemo.java` — Queue، Deque، PriorityQueue، ArrayDeque.

## خطاهای رایج
- `poll()` روی queue خالی → `null` برمی‌گرداند.
- `peek()` روی queue خالی → `null`.
- `offer` روی `Queue.of` → `UnsupportedOperationException`.
- `PriorityQueue` با `null` → `NullPointerException`.

## جمع‌بندی
- Queue = FIFO، Deque = هر دو سر، PriorityQueue = sorted.
- `offer`/`poll`/`peek` برای queue/deque.
- `ArrayDeque` سریع‌تر از `LinkedList` برای stack/queue.

**جلسهٔ بعد:** Generics.
