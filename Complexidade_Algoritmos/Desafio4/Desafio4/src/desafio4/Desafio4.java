/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio4;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TreeSet;

/**
 *
 * @author jonas
 */
public class Desafio4 {

    public int[] getInvalidValues(int max, int... moedas) {

        TreeSet<Integer> valids = new TreeSet<>();

        int i1;
        int i2;

        int v1;
        int v2;

        for (i1 = 0; i1 < moedas.length; i1++) {
            valids.add(moedas[i1]);
        }

        for (i1 = 1; i1 <= max; i1++) {
            if (!valids.contains(i1)) {
                continue;
            }
            for (i2 = i1; i2 <= max; i2++) {
                if (!valids.contains(i2)) {
                    continue;
                }
                if ((i1 + i2) > max) {
                    break;
                }
                if (!valids.contains(i1 + i2)) {
                    valids.add(i1 + i2);
                }
            }
        }

        int[] invalids = new int[max - valids.size()];

        i2 = 0;
        for (i1 = 1; i1 <= max; i1++) {
            if (!valids.contains(i1)) {
                invalids[i2] = i1;
                i2++;
            }
        }

        return invalids;
    }

    private void run() {
        long inicio = System.currentTimeMillis();

        System.out.println(Arrays.toString(getInvalidValues(10000, 3, 5)));
        System.out.println(Arrays.toString(getInvalidValues(10000, 30, 50, 3)));
        System.out.println(Arrays.toString(getInvalidValues(10000)));
        System.out.println(Arrays.toString(getInvalidValues(10000, 1)));
        System.out.println(Arrays.toString(getInvalidValues(10000, 2)));

        long fim = System.currentTimeMillis();
        System.out.println(new SimpleDateFormat("ss.SSS").format(new Date(fim - inicio)));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Desafio4().run();
    }

}
