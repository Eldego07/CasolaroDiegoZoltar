/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package casolarodiegozoltar;

import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author CASOLARO.DIEGO
 */
public class CasolaroDiegoClient {

    /*
    10.205.0.32
    10.205.0.50
    String ip = "10.205.0.22";
    String ip = "10.205.0.22";
    */ 
    int porta = 12345;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public CasolaroDiegoClient() throws IOException {
        socket = new Socket("127.0.0.1", porta);

        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    public String inviaMessaggio(String domanda,String personalita)throws IOException {
        output.writeUTF(personalita);
        output.writeUTF(domanda);
        output.flush();

        return input.readUTF();
    }

    public void chiudi() throws IOException {
        socket.close();
    }
}
