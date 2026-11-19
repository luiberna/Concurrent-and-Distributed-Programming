package iscot.client.actuators;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import iscot.client.gui.*;
import iscot.enums.*;
import iscot.events.*;
import java.net.Socket;

public class AirConditioning {
    public static void main(String[] args) throws NumberFormatException, InterruptedException, IOException {
        if (args.length != 3) {
            System.err.println("Invalid arguments!");
        }
        else {
            //Socket(String host, int port) estabelece uma conexão TCP com o servidor.
            //args[0] é o endereço IP ou hostname do servidor, e args[1] é a porta.
            Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            Areas area = Areas.valueOf(args[2]);
            output.writeObject(new RegistrationEvent(area, Type.ACTUATOR));
            output.flush();

            JFrame janela = new JFrame("Ar Condicionado em " + area);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            //Criar os componentes gráficos
            JPanel painel = new JPanel();
            painel.setLayout(new BorderLayout());
            ACIcon ACIcon = new ACIcon();
            painel.add(ACIcon, BorderLayout.CENTER);
            janela.setContentPane(painel);
			janela.setLocation(600, 400);
			janela.setMinimumSize(janela.getSize());
			janela.pack();
			janela.setVisible(true);

            // TODO: receive and handle messages from server

        }
    }
}
