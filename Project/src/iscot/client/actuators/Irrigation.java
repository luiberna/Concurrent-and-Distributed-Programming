package iscot.client.actuators;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import iscot.client.gui.*;
import iscot.enums.*;

public class Irrigation {
    public static void main(String[] args) throws NumberFormatException, InterruptedException {
        if (args.length != 3) {
            System.err.println("Invalid arguments!");
        }
        else {
            Areas area = Areas.valueOf(args[2]);
            JFrame janela = new JFrame("Irrigação em " + area);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Criar os componentes gráficos
            JPanel painel = new JPanel();
            painel.setLayout(new BorderLayout());
            IrrigationIcon irrigationIcon = new IrrigationIcon();
            painel.add(irrigationIcon, BorderLayout.CENTER);
            janela.setContentPane(painel);
            janela.setLocation(600, 400);
            janela.setMinimumSize(janela.getSize());
            janela.pack();
            janela.setVisible(true);

            //TODO: receive and handle messages from server

            //TODO: for first phase: create an appropriate event hardcoded locally and change the GUI accordingly
        }
    }
}
