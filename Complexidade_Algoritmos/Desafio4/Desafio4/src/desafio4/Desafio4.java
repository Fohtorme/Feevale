/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio4;

import java.lang.reflect.Array;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jonas
 */
public class Desafio4 {

    private ArrayList<Integer> loadPrimalNumbers(int max) {
        ArrayList<Integer> primes = new ArrayList();
        primes.add(2);

        boolean isPrime;

        for (int i = 3; i <= max; i += 2) {

            isPrime = true;

            int sqrtI = (int) Math.round(Math.sqrt(i));

            for (int j = 0; j < primes.size() && j < sqrtI; j++) {
                if ((i % primes.get(j)) == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                primes.add(i);
            }
        }

        return primes;
    }

    private boolean valorValido(int num, ArrayList<Integer> moedas) {
        if (num == 0) {
            return true;
        }
        for (int i = moedas.size() - 1; i >= 0; i--) {
            if (num >= moedas.get(i) && valorValido(num % moedas.get(i), moedas)) {
                return true;
            }
        }
        return false;
    }

    private int valoresInvalidos(int max, Integer... moedas) {

        Arrays.sort(moedas);

        ArrayList<Integer> mList = new ArrayList<>(Arrays.asList(moedas));

        if (mList.isEmpty()) {
            return max;
        }

        if (mList.get(0) == 1) {
            return 0;
        }
        for (int i = mList.size() - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (mList.get(i) % mList.get(j) == 0) {
                    mList.remove(i);
                    break;
                }
            }
        }

        int qtd = 0;
        for (int i = 1; i <= max; i++) {
            if (!valorValido(i, mList)) {
                qtd++;
            }
        }

        return qtd;
    }

    private void run() {
        long inicio = System.currentTimeMillis();

        System.out.println(valoresInvalidos(10000, 50, 30, 3));
        System.out.println(valoresInvalidos(10000));

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
