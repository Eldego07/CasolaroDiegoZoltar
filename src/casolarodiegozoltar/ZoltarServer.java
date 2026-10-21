/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casolarodiegozoltar;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Administrator
 */
public class ZoltarServer {
     private static final List<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {
        int porta = 12345;
        ServerSocket server = new ServerSocket(porta);
        System.out.println("Server avviato sulla porta " + porta + "...");

        while (true) {
            Socket clientSocket = server.accept();
            System.out.println("Nuovo client connesso: " + clientSocket.getInetAddress());

            ClientHandler handler = new ClientHandler(clientSocket);
            clients.add(handler); // Aggiunge il client alla lista

            new Thread(handler).start();
        }
    }

    // Metodo per inviare un messaggio a tutti gli altri client
    public static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) { // Non invia il messaggio al mittente stesso
                client.sendMessage(message);
            }
        }
    }
    public static void avvia() throws IOException {
        int porta = 12345;
        ServerSocket server = new ServerSocket(porta);

        System.out.println("Server avviato sulla porta " + porta);

        while (true) {
            Socket clientSocket = server.accept();

            ClientHandler handler = new ClientHandler(clientSocket);
            clients.add(handler);

            new Thread(handler).start();
        }
    }
}
