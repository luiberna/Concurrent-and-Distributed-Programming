import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue <T> {
    private Queue<T> queue = new LinkedList<>();
    private final int length;

    public BlockingQueue(int length) {
        if (length <= 0)
            throw new IllegalArgumentException();
        this.length = length;
    }

    public BlockingQueue() {
        length = -1;
    }

    public synchronized void put(T element) throws InterruptedException {
        while (length != -1 && queue.size() == length)
            wait();
        queue.add(element);
        notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty())
            wait();
        T element = queue.remove();
        if (length != -1)
            notifyAll();
        return element;
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized void clear() {
        queue.clear();
        if (length != -1)
            notifyAll();
    }
}
