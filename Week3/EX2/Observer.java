import java.util.Random;


public class Observer extends Thread {
    private static final int ITEMS_PER_THREAD = 3000;
    private final Queue queue;
    private final Random random = new Random();

    public Observer(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < ITEMS_PER_THREAD; i++) {
            queue.offer(random.nextInt(1000) + 1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int observerCount = 6;
        int expectedItems = observerCount * ITEMS_PER_THREAD;
        Queue queue = new Queue(expectedItems);
        Observer[] observer = new Observer[observerCount];

        for (int i = 0; i < observer.length; i++) {
            observer[i] = new Observer(queue);
            observer[i].start();
        }

        for (Observer process : observer) {
            process.join();
        }

        if (queue.size() != expectedItems) {
            throw new AssertionError("Expected " + expectedItems + " items, got " + queue.size());
        }

        for (int i = 0; i < expectedItems; i++) {
            if (queue.poll() == 0) {
                throw new AssertionError("Queue contains zero");
            }
        }

        System.out.println("Verified " + expectedItems + " nonzero items.");
    }
}
