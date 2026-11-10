package iscot.client.sensors;

import java.awt.BorderLayout;

import iscot.enums.*;
import iscot.events.*;
import iscot.client.gui.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LightSensor {
    public static void mains(String[] args) throws NumberFormatException, InterruptedException {
        if (args.length != 3) {
            System.err.println("Invalid arguments!");
        }
        else {
            // TODO connect to the server

            // TODO register with the server
            Areas area = Areas.valueOf(args[2]);

            JFrame janela = new JFrame("Luz em " + area);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Criar os componentes gráficos
            JPanel painel = new JPanel();
            painel.setLayout(new BorderLayout());
            SensorControl SensorControl = new SensorControl("💡", "Luz - " + area, "%",
                    0, 100, 1, 50,
                    valor -> {
                        // TODO handle change in sensor
                        LightSensorEvent LSEvent = new LightSensorEvent(area, valor);
                        System.out.println("Enviando evento: " + LSEvent);
                        System.out.println("Mudou para:" + valor);
                    });
            painel.add(SensorControl, BorderLayout.CENTER);
            janela.setContentPane(painel);
            janela.setLocation(600, 400);
            janela.setMinimumSize(janela.getSize());
            janela.pack();
            janela.setVisible(true);
        }
    }
}
