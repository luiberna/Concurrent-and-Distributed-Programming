package iscot.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import iscot.events.Event;

public final class ClientHandler implements Runnable {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String clientAddress = socket.getRemoteSocketAddress().toString();
        System.out.println("Cliente conectado: " + clientAddress);

        try (Socket clientSocket = socket;
             ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream())) {
            while (true) {
                Object message = input.readObject();
                if (message instanceof Event)
                    System.out.println("Evento recebido de " + clientAddress + ": " + message);
            }
        } catch (EOFException e) {
            System.out.println("Cliente desconectado: " + clientAddress);
        } catch (ClassNotFoundException e) {
            System.err.println("Mensagem inválida de " + clientAddress + ": " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro na ligação com " + clientAddress + ": " + e.getMessage());
        }
    }
}
