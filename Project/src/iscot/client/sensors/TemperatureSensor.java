package iscot.client.sensors;

import java.awt.BorderLayout;
import java.io.IOException;

import iscot.client.*;
import iscot.enums.*;
import iscot.events.*;
import iscot.client.gui.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TemperatureSensor {
    public static void main(String[] args) throws NumberFormatException, InterruptedException {
        if (args.length != 3) {
            System.err.println("Invalid arguments!");
        }
        else {
            // TODO connect to the server

            // TODO register with the server
            Areas area = Areas.valueOf(args[2]);

            JFrame janela = new JFrame("Temperatura em " + area);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Criar os componentes gráficos
            JPanel painel = new JPanel();
            painel.setLayout(new BorderLayout());
            SensorControl SensorControl = new SensorControl("🌡", "Temperatura - " + area, "°C",
                    -20, 50, 1, 20,
                    valor -> {
                        // TODO handle change in sensor
                        TemperatureSensorEvent TSEvent = new TemperatureSensorEvent(area, valor);
                        System.out.println("Enviando evento: " + TSEvent);
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
