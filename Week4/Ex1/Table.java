import java.util.LinkedList;
import java.util.Queue;

public class Table {
    private final Queue<Boar> boars = new LinkedList<>();
    private final int capacity;

    public Table(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void serve(Boar boar) throws InterruptedException {
        while (boars.size() == capacity) {
            System.out.println(Thread.currentThread().getName() + 
            " waiting for the gaulish to eat, the table is full");
            wait();
        }
        boars.add(boar);

        // System.out.println(boar + " was served there is still space for " +
        // (capacity - boars.size()) + " cooked boars!");
        notifyAll();
    }

    public synchronized Boar eat() throws InterruptedException {
        while (boars.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + 
            " the gaulish are waiting for boar!");
            wait();
        }
        Boar boar = boars.remove();
        // System.out.println(Thread.currentThread().getName() + " removed " + boar + ". The table now has space for: "
        // + (capacity - boars.size()) + " boars.");
        notifyAll();
        return boar;
    }
}
