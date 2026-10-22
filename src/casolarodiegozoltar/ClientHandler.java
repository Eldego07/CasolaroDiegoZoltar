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
        try {
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            while (true) {
                String personalita = input.readUTF();
                String domanda = input.readUTF();

                Personalita p;
                if (personalita.equals("Ottimista")) {
                    p = new Ottimista();
                } else if (personalita.equals("Pessimista")) {
                    p = new Pessimista();
                } else {
                    p = new Default();
                }

                String risposta = p.rispondi();
                output.writeUTF(risposta);
                output.flush();

                System.out.println("Domanda: " + domanda);
                System.out.println("Risposta: " + risposta);
            }

        } catch (IOException e) {
            System.out.println("Client disconnesso.");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Errore chiusura socket.");
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
