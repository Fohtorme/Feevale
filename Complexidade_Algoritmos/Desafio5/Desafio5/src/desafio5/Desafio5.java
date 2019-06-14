/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio5;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jonas
 */
public class Desafio5 {

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

    private int[] rodinhaDeAmigos(int n) {
        // Se for um número impar
        if (n % 2 != 0) {
            return new int[]{-1};
        }
        // Se for um número divisível por 4
        if (n % 4 == 0) {
            return new int[]{-1};
        }
        // Carrega números primos de valores até o dobro do máximo
        // Ex: A soma do último número com o penultimo é (n-1)+n ou 2n-1
        List primos = loadPrimalNumbers(n * 2);
        // Matrix with all possibilities of matched numbers
        boolean[][] m = new boolean[n / 2][n / 2];
        // Aux int variables
        int i, j, x, y;
        // Relaciona todos números impares com números pares
        for (i = 1; i <= n; i += 2) {
            for (j = 2; j <= n; j += 2) {
                if (primos.contains(i + j)) {
                    m[(i - 1) / 2][(j - 2) / 2] = true;
                }
            }
        }

        int[] arr1 = new int[n / 2];
        int[] arr2 = new int[n / 2];

        for (i = 0; i < n / 2; i++) {
            arr1[i] = i;
            arr2[i] = i;
        }

        return arr1;
    }

    private void run() {
        long inicio = System.currentTimeMillis();

//        System.out.println(Arrays.toString(rodinhaDeAmigos(6)));
        System.out.println(Arrays.toString(rodinhaDeAmigos(8)));
//        System.out.println(Arrays.toString(rodinhaDeAmigos(10)));
//        System.out.println(Arrays.toString(rodinhaDeAmigos(12)));
//        System.out.println(Arrays.toString(rodinhaDeAmigos(14)));
//        System.out.println(Arrays.toString(rodinhaDeAmigos(16)));
        System.out.println(Arrays.toString(rodinhaDeAmigos(18)));
//        System.out.println(Arrays.toString(rodinhaDeAmigos(19)));
//        System.out.println(Arrays.toString(rodinhaDeAmigos(42)));

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
