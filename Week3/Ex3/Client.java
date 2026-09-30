import java.util.Random;

public class Client extends Thread {
    private Account account;
    public int totalAmount = 0;

    public Client(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!isInterrupted()) {
            int amount = random.nextInt(100) + 1;
            totalAmount += amount;
            account.deposit(amount);
            System.out.println("Depositado: " + amount + ", Saldo atual: " + account.getBalance());
        }
    }
}
