import java.util.Random;

public class NameThread extends Thread {

    int id;

    public NameThread(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println("id: " + id);

            int time = 1000 + random.nextInt(1001);
            
            try {
                sleep(time);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public static void main(String[] args) {

        NameThread t1 = new NameThread(1);
        NameThread t2 = new NameThread(2);

        t1.start();
        t2.start();

        try {
            Thread.sleep(4000);

            t1.interrupt();
            t2.interrupt();

            t1.join();
            t2.join();

        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        System.out.println("Completed");
    }
}