import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadB extends Thread {
    private final Random random = new Random();
    private final AtomicInteger generatedNumbers;

    public ThreadB(AtomicInteger generatedNumbers) {
        this.generatedNumbers = generatedNumbers;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            //Want to print numbers from 1 to 9
            System.out.println("ThreadB: " + random.nextInt(9) + 1);
            generatedNumbers.incrementAndGet();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
