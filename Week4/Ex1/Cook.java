public class Cook extends Thread{
    private final Table table;
    private boolean hasBoar;
    private final String name;
    private Boar boar;

    private final int quantity;

    public Cook(String name, Table table, int quantity) {
        this.name = name;
        this.hasBoar = false;
        this.table = table;
        this.boar = null;
        this.quantity = quantity;
    }

    private void cookBoar(int number) {
        System.out.println("Cook " + name + " is cooking a Boar!");
        this.boar = new Boar(this.name, number);
        this.hasBoar = true;
    }

    private void serveBoar() throws InterruptedException {
        table.serve(this.boar);
        System.out.println("Cook " + name + " served a Boar!");
        this.boar = null;
        this.hasBoar = false;
    }

    @Override 
    public void run() {
        int number = 1;
        try {
            while (!isInterrupted() && (quantity == -1|| number <= this.quantity)) {
                if (hasBoar == false)
                    cookBoar(number++);
                serveBoar();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
