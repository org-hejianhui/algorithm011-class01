import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 用 add first 或 add last 这套新的 API 改写 Deque 的代码
 */
public class DequeDemo {

    public static void main(String[] args) {
        dequeTest3();
    }

    private static void dequeTest1() {
        Deque<String> deque = new LinkedList<>();
        deque.push("a");
        deque.push("b");
        deque.push("c");
        deque.push("d");

        System.out.println(deque.peek());
        System.out.println(deque);

        while (deque.size() > 0){
            System.out.println(deque.pop());
        }
        System.out.println(deque);
    }

    private static void dequeTest2() {
        Deque<String> deque = new ArrayDeque<>();
        deque.push("a");
        deque.push("b");
        deque.push("c");
        deque.push("d");

        System.out.println(deque.peek());
        System.out.println(deque);

        while (deque.size() > 0){
            System.out.println(deque.pop());
        }
        System.out.println(deque);
    }

    private static void dequeTest3() {
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        deque.addFirst("d");

        System.out.println(deque.getFirst());
        System.out.println(deque);

        while (deque.size() > 0){
            System.out.println(deque.removeFirst());
        }
        System.out.println(deque);
    }
}