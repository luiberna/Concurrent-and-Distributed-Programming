package iscot.client.actuators;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import iscot.client.gui.*;
import iscot.enums.*;
import iscot.events.RegistrationEvent;

public class Irrigation {
    public static void main(String[] args) throws NumberFormatException, InterruptedException, IOException{
        if (args.length != 3) {
            System.err.println("Invalid arguments!");
        }
        else {
            //Socket(String host, int port) estabelece uma conexão TCP com o servidor.
            //args[0] é o endereço IP ou hostname do servidor, e args[1] é a porta.
            Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            Areas area = Areas.valueOf(args[2]);
            out.writeObject(new RegistrationEvent(area, Type.ACTUATOR));

            JFrame janela = new JFrame("Irrigação em " + area);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            janela.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent event) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        System.err.println("Could not close the socket: " + e.getMessage());
                    }
                }
            });

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
