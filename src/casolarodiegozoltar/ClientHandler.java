/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casolarodiegozoltar;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author tadde
 */
class ClientHandler implements Runnable {

   private final Socket clientSocket;
    private DataOutputStream dataOutputStream;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream())) {
            this.dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            // Primo messaggio
            dataOutputStream.writeUTF("Ciao sono Zoltar! (Scrivi Help per i comandi)");

            String clientMessage;
            String message;

            while (true) {
                clientMessage = dataInputStream.readUTF();
                System.out.println("[" + clientSocket.getInetAddress() + "] Ricevuto: " + clientMessage);

                if (clientMessage.equals("Arrivederci")) {
                    dataOutputStream.writeUTF("Arrivederci");
                    break;
                } else {
                    // Invia una risposta al client per completare lo scambio messaggi
                    String formattedMessage = "[" + clientSocket.getInetAddress() + "]: " + clientMessage;
                    CasolaroDiegoZoltar.broadcast(formattedMessage, this);
                }

            }

        } catch (IOException e) {
            System.out.println("Connessione con il client interrotta: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Client disconnesso.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void sendMessage(String message) {
        try {
            if (dataOutputStream != null) {
                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();
            }
        } catch (IOException e) {
            System.out.println("Errore nell'invio del messaggio al client: " + e.getMessage());
        }
    }
}
