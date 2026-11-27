// QueueDequeDemo.java — Queue، Deque، PriorityQueue، ArrayDeque
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueDequeDemo {

    public static void main(String[] args) {
        // ---------- Queue — FIFO ----------
        Queue<String> queue = new LinkedList<>();
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");
        System.out.println("queue=" + queue);
        System.out.println("poll()=" + queue.poll());   // First
        System.out.println("peek()=" + queue.peek());   // Second
        System.out.println("queue after=" + queue);     // [Third]

        // ---------- Deque — هر دو سر ----------
        Deque<String> deque = new ArrayDeque<>();
        deque.offerFirst("Front");
        deque.offerLast("Back");
        deque.offer("Middle");          // به آخر
        System.out.println("\ndeque=" + deque);   // [Front, Back, Middle]
        System.out.println("pollFirst()=" + deque.pollFirst());  // Front
        System.out.println("pollLast()=" + deque.pollLast());    // Middle
        System.out.println("deque after=" + deque);              // [Back]

        // ---------- PriorityQueue — sorted ----------
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(5);
        pq.offer(1);
        pq.offer(3);
        pq.offer(2);
        System.out.println("\npq=" + pq);   // [1, 3, 2, 5] یا مشابه — heap
        System.out.println("poll()=" + pq.poll());   // 1 — کوچک‌ترین
        System.out.println("poll()=" + pq.poll());   // 2
        System.out.println("peek()=" + pq.peek());   // 3
        System.out.println("pq after=" + pq);        // [5]

        // ---------- PriorityQueue با String ----------
        PriorityQueue<String> names = new PriorityQueue<>();
        names.offer("Zebra");
        names.offer("Apple");
        names.offer("Mango");
        System.out.println("\npoll=" + names.poll());   // Apple — الفبایی
        System.out.println("poll=" + names.poll());   // Mango
        System.out.println("poll=" + names.poll());   // Zebra

        // ---------- ArrayDeque به‌عنوان Stack ----------
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(1);   // push به اول
        stack.push(2);
        stack.push(3);
        System.out.println("\nstack pop()=" + stack.pop());   // 3 — LIFO
        System.out.println("stack pop()=" + stack.pop());   // 2
    }
}
