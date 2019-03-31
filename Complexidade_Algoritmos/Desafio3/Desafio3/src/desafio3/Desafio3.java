/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio3;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author jonas
 */
public class Desafio3 {

    public int evenNumbersInTriangle(int lines) {
        boolean[] pair = new boolean[lines];
        boolean[] pairAux = new boolean[lines];
        int qtd = 0;

        for (int i = 2; i < lines; i++) {
            for (int j = 1; j < i; j++) {
                if (pair[j - 1] ^ pair[j]) {
                    pairAux[j] = false;
                } else {
                    pairAux[j] = true;
                    qtd++;
                }
            }
            pair = pairAux.clone();
        }
        return qtd;
    }

    private void run() {
        long inicio = System.currentTimeMillis();

        System.out.println(evenNumbersInTriangle(1_000));

        long fim = System.currentTimeMillis();
        System.out.println(new SimpleDateFormat("ss.SSS").format(new Date(fim - inicio)));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Desafio3().run();
    }

}
