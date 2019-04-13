/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio5;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class Desafio5 {

    private int bestCombination(int total, int... possibilities) {
        if (total == 0) {
            return 0;
        }
        for (int i = 0; i < possibilities.length; i++) {
            if (total >= possibilities[i]) {
                int val = bestCombination(total % possibilities[i], possibilities);
                if (val != -1) {
                    return total / possibilities[i] + val;
                }
            }
        }
        return -1;
    }

    private void run() {
        long inicio = System.currentTimeMillis();

        System.out.println(bestCombination(55, 3, 5));
        System.out.println(bestCombination(54, 3, 5));

        long fim = System.currentTimeMillis();
        System.out.println(new SimpleDateFormat("ss.SSS").format(new Date(fim - inicio)));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Desafio5().run();
    }

}
