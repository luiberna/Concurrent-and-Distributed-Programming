import javax.swing.JTextField;

public class Scale {
    private static final double INGOT_WEIGHT = 12.5;

    private double weight;
    private final JTextField textField;

    public Scale(JTextField textField) {
        this.textField = textField;
        weight = 0.0;
    }

    public synchronized void addGold(double gold) throws InterruptedException {
        while (weight >= INGOT_WEIGHT) {
            System.out.println(Thread.currentThread() + " - before wait.");
            wait();
            System.out.println(Thread.currentThread() + " - after wait");
        }
        weight += gold;
        textField.setText(String.valueOf(weight));
        notifyAll();
    }

    public synchronized double removeGold() throws InterruptedException {
        while (weight < INGOT_WEIGHT) {
            System.out.println(Thread.currentThread() + " - before wait.");
            wait();
            System.out.println(Thread.currentThread() + " - after wait");
        }
        weight -= INGOT_WEIGHT;
        textField.setText(String.valueOf(weight));
        notifyAll();

        return INGOT_WEIGHT;
    }
}
