public class Account {
    private double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
    }

    public synchronized void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }

}
