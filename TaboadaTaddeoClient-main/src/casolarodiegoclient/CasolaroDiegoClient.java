/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package casolarodiegoclient;

import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author CASOLARO.DIEGO
 */
public class CasolaroDiegoClient {

    /**
     * @param args the command line argumentsv  10.205.0.32
     * 10.205.0.50
     */
    public static void main(String[] args) throws IOException {
        //String ip = "10.205.0.22";
        String ip = "10.205.0.22";
        int porta = 12345;
        
        try (
            Socket socket = new Socket(ip, porta);
            Scanner scanner = new Scanner(System.in);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())
        ) {
            // Ricezione benvenuto
            String response = dataInputStream.readUTF();
            System.out.println(response);

            // Primo messaggio
            dataOutputStream.writeUTF("Ciao io sono Eldego e sono collegato!");
            response = dataInputStream.readUTF();
            System.out.println(response);

            if ("Arrivederci".equals(response)) {
                System.out.println("Connessione chiusa dal server.");
            } else {
                String message;
                do {
                    System.out.print("messaggia: ");
                    message = scanner.nextLine();
                    dataOutputStream.writeUTF(message);

                    response = dataInputStream.readUTF();
                    System.out.println(response);

                } while (!"Arrivederci".equals(response) && !"Arrivederci".equals(message));
            }
        }
    }
}
