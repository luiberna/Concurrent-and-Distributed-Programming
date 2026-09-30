public class Queue {
    private final int[] elements;
    private int head;
    private int count;

    public Queue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        elements = new int[capacity];
    }

    public synchronized boolean empty() {
        return count == 0;
    }

    public synchronized int peek() {
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements[head];
    }

    public synchronized int size() {
        return count;
    }

    public synchronized int poll() {
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int value = elements[head];
        head = (head + 1) % elements.length;
        count--;
        return value;
    }

    public synchronized void offer(int item) {
        if (count == elements.length) {
            throw new IllegalStateException("Queue is full");
        }
        int tail = (head + count) % elements.length;
        elements[tail] = item;
        count++;
    }
}
