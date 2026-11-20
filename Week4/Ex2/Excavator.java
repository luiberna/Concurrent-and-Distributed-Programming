import java.util.Random;

public class Excavator extends Thread {

    private final Scale scale;
    private final Random random = new Random();


    public Excavator(Scale scale) {
        this.scale = scale;
    }

    @Override 
    public void run() {
        System.out.println(Thread.currentThread() + " - start of run.");
        try {
            while (!isInterrupted()) {
                double gold = random.nextDouble(0.0, 1.0);
                scale.addGold(gold);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().toString() + " - before treating InterruptedException.");
        }
        System.out.println(Thread.currentThread() + " - end of Run.");
    }
}
