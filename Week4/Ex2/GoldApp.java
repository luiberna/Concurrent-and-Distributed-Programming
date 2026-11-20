import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GoldApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gold Scale");
        JTextField textField = new JTextField("0.0");
        JButton stopButton = new JButton("Stop");

        textField.setEditable(false);

        frame.add(textField, BorderLayout.CENTER);
        frame.add(stopButton, BorderLayout.SOUTH);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Scale scale = new Scale(textField);
        Excavator excavator = new Excavator(scale);
        Goldsmith goldsmith = new Goldsmith("Goldsmith", scale);

        stopButton.addActionListener(event -> {
            stopButton.setEnabled(false);

            excavator.interrupt();
            goldsmith.interrupt();

            try {
                System.out.println(Thread.currentThread().toString()
                        + " - before excavator join.");
                excavator.join();

                System.out.println(Thread.currentThread().toString()
                        + " - before goldsmith join.");
                goldsmith.join();
            } catch (InterruptedException exception) {
                System.out.println(Thread.currentThread().toString()
                        + " - before treating InterruptedException.");
            }

            frame.dispose();
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                stopButton.doClick();
            }
        });

        frame.setVisible(true);
        excavator.start();
        goldsmith.start();
    }
}
