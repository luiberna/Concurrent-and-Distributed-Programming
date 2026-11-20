public class Goldsmith extends Thread {
    private final Scale scale;
    private int ingotsCreated;
    private final String name;

    public Goldsmith(String name, Scale scale) {
        this.scale = scale;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + " - start of run.");
        try {
            while (!isInterrupted()) {
                scale.removeGold();
                System.out.println(Thread.currentThread() + " - before sleep.");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread() + " - after sleep.");
                ingotsCreated++;
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().toString() + " - before treating InterruptedException.");
        }
        System.out.println(Thread.currentThread() + " - total of ingots: " + ingotsCreated);
        System.out.println(Thread.currentThread() + " - end of run.");
    }

}
