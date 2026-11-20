public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new BlockingQueue<>(3);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException exception) {
                System.out.println("Producer interrupted.");
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    Thread.sleep(500);
                    int value = queue.take();
                    System.out.println("Consumed: " + value);
                }
            } catch (InterruptedException exception) {
                System.out.println("Consumer interrupted.");
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Final queue size: " + queue.size());
    }
}
