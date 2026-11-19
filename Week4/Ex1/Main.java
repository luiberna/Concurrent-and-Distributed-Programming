public class Main {
    public static void main(String[] args)
            throws InterruptedException {

        Table table = new Table(10);

        Cook cook1 = new Cook("Martin", table, 10);
        Cook cook2 = new Cook("Jeff", table, 10);

        Gaulish gaulish1 = new Gaulish("Caturix", table);
        Gaulish gaulish2 = new Gaulish("Brennus", table);

        cook1.start();
        cook2.start();
        gaulish1.start();
        gaulish2.start();

        Thread.sleep(10000);

        cook1.interrupt();
        cook2.interrupt();
        gaulish1.interrupt();
        gaulish2.interrupt();

        cook1.join();
        cook2.join();
        gaulish1.join();
        gaulish2.join();

        System.out.println("The banquet has ended!");
    }
}