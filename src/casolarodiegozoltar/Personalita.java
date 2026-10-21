/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casolarodiegozoltar;

/**
 *
 * @author Administrator
 */
import java.util.Random;

public class Personalita {

    protected String[] messaggi;

    public String rispondi() {
        Random random = new Random();

        int numero = random.nextInt(messaggi.length);

        return messaggi[numero];
    }
}
