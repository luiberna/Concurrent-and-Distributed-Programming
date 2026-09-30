import java.awt.FlowLayout;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Observer {
    public static void main(String[] args) {
        AtomicInteger generatedNumbers = new AtomicInteger();
        ThreadA threadA = new ThreadA(generatedNumbers);
        ThreadB threadB = new ThreadB(generatedNumbers);

        JFrame frame = new JFrame("Random number generator");
        JButton button = new JButton("Stop");
        JLabel label = new JLabel("Generated numbers: 0");
        Timer timer = new Timer(100, event ->
                label.setText("Generated numbers: " + generatedNumbers.get()));

        frame.setLayout(new FlowLayout());
        frame.add(label);
        frame.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setVisible(true);

        button.addActionListener(event -> {
            threadA.interrupt();
            threadB.interrupt();
            timer.stop();
            frame.dispose();
        });

        timer.start();
        threadA.start();
        threadB.start();
    }
}
