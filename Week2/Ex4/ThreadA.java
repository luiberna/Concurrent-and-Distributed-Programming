import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadA extends Thread {
    private final Random random = new Random();
    private final AtomicInteger generatedNumbers;

    public ThreadA(AtomicInteger generatedNumbers) {
        this.generatedNumbers = generatedNumbers;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            //Want to print numbers from 1000 to 9999
            System.out.println("ThreadA: " + (random.nextInt(9000) + 1000));
            generatedNumbers.incrementAndGet();
        }
    }
}
