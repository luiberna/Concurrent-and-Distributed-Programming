package iscot.client.actuators;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import iscot.client.gui.*;
import iscot.enums.*;

public class Light {
    public static void main(String[] args) throws NumberFormatException, InterruptedException {
        if (args.length != 3) {
            System.err.println("Invalid arguments!");
        }
        else {
            // TODO connect to the server

            // TODO register with the server
            Areas area = Areas.valueOf(args[2]);
            JFrame janela = new JFrame("Luz em " + area);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            LightIcon lightIcon = new LightIcon();
            JPanel painel = new JPanel();
            painel.setLayout(new BorderLayout());
            painel.add(lightIcon, BorderLayout.CENTER);
            janela.setContentPane(painel);
            janela.setLocation(600, 400);
            janela.setMinimumSize(janela.getSize());
            janela.pack();
            janela.setVisible(true);
        }
    }
}
