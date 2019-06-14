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
        // Carrega números primos de valores até o dobro do máximo
        // Ex: A soma do último número com o penultimo é (n-1)+n ou 2n-1
        List primos = loadPrimalNumbers(n * 2);

        boolean[][] m = new boolean[n / 2][n / 2];
        int[] cont = new int[n];

        int i, j, x, y;

        for (i = (n / 4); i >= 0; i--) {
            for (j = (n / 2); j >= 0; j--) {
                x = 0 + j;
                y = 0 + j + i;
                if (y < n / 2) {
                    if (primos.contains((1 + x * 2) + (2 + y * 2))) {
                        if (!m[x][y]
                                && cont[x * 2] < 3
                                && cont[1 + y * 2] < 3) {
                            m[x][y] = true;
                            cont[x * 2]++;
                            cont[1 + y * 2]++;
                        }
                        if (!m[y][x]
                                && cont[y * 2] < 3
                                && cont[1 + x * 2] < 3) {
                            m[y][x] = true;
                            cont[y * 2]++;
                            cont[1 + x * 2]++;
                        }
//                        m[x][y] = true;
//                        m[y][x] = true;
                    }
                }
                x = 0 + j;
                y = (n / 2 - 1) + j + -i;
                if (x < n / 2 && y >= 0 && y < n / 2) {
                    if (primos.contains(1 + x * 2 + 2 + y * 2)) {
                        if (!m[x][y]
                                && cont[x * 2] < 3
                                && cont[1 + y * 2] < 3) {
                            m[x][y] = true;
                            cont[x * 2]++;
                            cont[1 + y * 2]++;
                        }
                        if (!m[y][x]
                                && cont[y * 2] < 3
                                && cont[1 + x * 2] < 3) {
                            m[y][x] = true;
                            cont[y * 2]++;
                            cont[1 + x * 2]++;
                        }
//                        m[x][y] = true;
//                        m[y][x] = true;
                    }
                }
                
            }
        }

        int[] r = new int[n];
        int[] p = new int[3];
        int ra = -1;

//        x = -1;
//        r[x] = 1;
//        while (true) {
//            y = 0;
//            if (r[x] % 2 == 0) {
//                for (i = 0; i < n / 2; i++) {
//                    if (m[r[x] / 2][i]) {
//                        p[y++] = 0 + i * 2;
//                    }
//                }
//            } else {
//                for (i = 0; i < n / 2; i++) {
//                    if (m[i][r[x] / 2]) {
//                        p[y++] = 1 + i * 2;
//                    }
//                }
//            }
//            r[x + n / 2 - (((x + n / 2) < n) ? 0 : n)] = p[0];
//            r[++x] = p[1];
//            if (ra != -1) {
//                ra = p[2];
//            }
//        }
        for (int k = 0; k < m.length; k++) {
            System.out.println(Arrays.toString(m[k]));
        }
        
        
        List pos = new ArrayList<Integer[]>();
        for (i = 0; i < n/2; i++) {
            for (j = 1; j < n/2; j++) {
                pos.add(new int[]{18});
            }
        }

        return cont;
    }

    private void run() {
        long inicio = System.currentTimeMillis();

//        System.out.println(Arrays.toString(rodinhaDeAmigos(6)));
//        System.out.println(Arrays.toString(rodinhaDeAmigos(8)));
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
