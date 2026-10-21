/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package casolarodiegozoltar;

import static java.awt.Frame.MAXIMIZED_BOTH;
import java.io.*;



/**
 *
 * @author CASOLARO.DIEGO
 */
public class CasolaroDiegoZoltar {

     /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                ZoltarServer.avvia();
            } catch (IOException e) {
                System.out.println("Errore server: " + e.getMessage());
            }
        }).start();

        java.awt.EventQueue.invokeLater(() -> {
            Zoltar frmZoltar = new Zoltar();
            frmZoltar.setVisible(true);
        });
    }

    // Metodo per inviare un messaggio a tutti gli altri client
    
}
