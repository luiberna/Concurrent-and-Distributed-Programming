import javax.swing.*;
import java.util.Random;
import java.awt.*;

public class Horse extends Thread {

    private JTextField textField;
    private Random random;

    private JTextField horse1Field;
    private JTextField horse2Field;
    private JTextField horse3Field;
    private JButton startButton;

    public Horse() {
        this.random = new Random();
    }

    public Horse(JTextField textField) {
        this.textField = textField;
        this.random = new Random();
    }

    public void open() {

        JFrame frame = new JFrame("Horse Race");

        horse1Field = new JTextField("Horse 1");
        horse2Field = new JTextField("Horse 2");
        horse3Field = new JTextField("Horse 3");

        startButton = new JButton("Start Race");

        JPanel horsePanel = new JPanel();
        horsePanel.setLayout(new GridLayout(3, 1));

        horsePanel.add(horse1Field);
        horsePanel.add(horse2Field);
        horsePanel.add(horse3Field);

        JPanel horsesPanel = new JPanel();
        horsesPanel.setLayout(new BorderLayout());

        horsesPanel.add(horsePanel, BorderLayout.CENTER);
        horsesPanel.add(startButton, BorderLayout.SOUTH);

        frame.add(horsesPanel);

        startButton.addActionListener(e -> startRace());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void startRace() {

        horse1Field.setText("Horse 1");
        horse2Field.setText("Horse 2");
        horse3Field.setText("Horse 3");

        Horse horse1 = new Horse(horse1Field);
        Horse horse2 = new Horse(horse2Field);
        Horse horse3 = new Horse(horse3Field);

        horse1.start();
        horse2.start();
        horse3.start();
    }

    @Override
    public void run() {

        for (int moves = 30; moves >= 0; moves--) {

            textField.setText("" + moves);

            try {
                Thread.sleep(random.nextInt(101));
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        Horse window = new Horse();
        window.open();
    }
}