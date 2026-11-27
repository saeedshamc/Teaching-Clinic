# پاسخ تمرین‌های جلسه ۲۸

## آسان

**۱.**
```java
Queue<Integer> q = new LinkedList<>();
q.offer(1); q.offer(2); q.offer(3); q.offer(4); q.offer(5);
while (!q.isEmpty()) System.out.println(q.poll());   // 1 2 3 4 5 — FIFO
```

**۲.**
```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.offer(5); pq.offer(1); pq.offer(3); pq.offer(2); pq.offer(4);
while (!pq.isEmpty()) System.out.println(pq.poll());   // 1 2 3 4 5 — sorted
```

## متوسط

**۳.**
```java
Deque<String> d = new ArrayDeque<>();
d.offerFirst("First");
d.offerLast("Last");
d.offer("Middle");
System.out.println(d.pollFirst());   // First
System.out.println(d.pollLast());    // Middle
System.out.println(d.peek());        // Last
```

**۴.**
```java
ArrayDeque<Integer> stack = new ArrayDeque<>();
stack.push(1); stack.push(2); stack.push(3);
System.out.println(stack.pop());   // 3 — LIFO
System.out.println(stack.pop());   // 2
System.out.println(stack.pop());   // 1
```

## سخت

**۵.**
```java
Queue<String> bankQueue = new LinkedList<>();
bankQueue.offer("Customer1");
bankQueue.offer("Customer2");
bankQueue.offer("Customer3");
String next = bankQueue.poll();   // Customer1 — اول می‌آید
System.out.println("Now serving: " + next);
```

**۶.** در جلسهٔ ۳۰ با `new PriorityQueue<>(Comparator.reverseOrder())` مرتب‌سازی نزولی را ببینید.
