public class Gaulish extends Thread {
    private final String name;
    private final Table table;


    public Gaulish(String name, Table table) {
        this.name = name;
        this.table = table;
    }

    private void eat() throws InterruptedException {
        Boar boar = table.eat();
        System.out.println("The gaulish " + name + " ate " + boar);
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                eat();
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
}
