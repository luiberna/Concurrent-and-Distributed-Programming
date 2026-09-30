public class RunTest {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(0);
        Client[] clients = new Client[10];
        int totalAmount = 0;
        for (int i = 0; i < clients.length; i++) {
            clients[i] = new Client(account);
            clients[i].start();
        }

        Thread.sleep(10000);
        for (Client client : clients) {
            client.interrupt();
        }
        for (Client client : clients) {
            client.join();
        }

        for (int i = 0; i < clients.length; i++) {
            System.out.println("Client " + i + " deposited " + clients[i].totalAmount);
            totalAmount += clients[i].totalAmount;
        }

        double balance = account.getBalance();
        System.out.printf("Account balance: %.0f%n", balance);
        System.out.println("Clients' total: " + totalAmount);
        if (Double.compare(balance, totalAmount) == 0) {
            System.out.println("Totals match.");
        } else {
            System.out.println("Totals differ.");
        }
    }
}