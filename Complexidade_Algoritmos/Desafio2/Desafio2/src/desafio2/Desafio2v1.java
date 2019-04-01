/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio2;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class Desafio2v1 {

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

    private int firstLesserThan(int number, ArrayList<Integer> array) {
        // TODO: Make it faster
        int index = -1;
        for (int i = 0; i < array.size() && array.get(i) < number; i++) {
            index = i;
        }
        return index;
    }

    private ArrayList<String> getGoldbachSets(int number, ArrayList<Integer> primalNumbers) {
        int minIndex = 0;
        int maxIndex = firstLesserThan(number, primalNumbers);
        int val;
        ArrayList<String> goldbachSets = new ArrayList();
        while (true) {
            if (minIndex > maxIndex) {
                break;
            }
            val = primalNumbers.get(minIndex) + primalNumbers.get(maxIndex);
            if (val == number) {
                goldbachSets.add("("
                        + primalNumbers.get(minIndex) + ","
                        + primalNumbers.get(maxIndex) + ")");
                minIndex++;
                maxIndex--;
                continue;
            }
            if (val > number) {
                maxIndex--;
                continue;
            }
            if (val < number) {
                minIndex++;
                continue;
            }
        }
        return goldbachSets;
    }

    private void greaterGoldbachSets(int min, int max) {
        ArrayList<Integer> primalNumbers = loadPrimalNumbers(max);

        ArrayList<String> gSets;

        int[] eCount = new int[3];
        String[] eFormated = new String[3];
        int[] eNumber = new int[3];

        int index;

        for (int g = min; g <= max; g += 2) {
            index = 0;
            for (int e = 1; e < eCount.length; e++) {
                if (eCount[e] < eCount[index]) {
                    index = e;
                }
            }
            gSets = getGoldbachSets(g, primalNumbers);
            if (eCount[index] < gSets.size()) {
                eCount[index] = gSets.size();
                eFormated[index] = gSets.toString();
                eNumber[index] = g;
            }
        }

        for (int i = 0; i < 3; i++) {
            System.out.println("G(" + eNumber[i] + ")=" + eFormated[i]);
        }
    }

    private void run() {
        long inicio = System.currentTimeMillis();

        greaterGoldbachSets(1000000, 1000010);

        long fim = System.currentTimeMillis();
        System.out.println(new SimpleDateFormat("ss.SSS").format(new Date(fim - inicio)));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Desafio2v1().run();
    }

}
