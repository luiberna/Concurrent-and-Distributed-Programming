package iscot.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import iscot.enums.*;
import iscot.events.*;

public class Server {
    private final int port;
    private final HashMap<Integer, Socket> clients = new HashMap<>();
    private int nextClientId = 1;

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("IscoT Server iniciado na porta " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                int clientId = nextClientId++;
                clients.put(clientId, socket);
            }
        } catch (IOException e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: java iscot.server.Server <porta>");
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);
        Server server = new Server(port);
        server.start();
    }
}
