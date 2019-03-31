/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio2;

import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class Desafio2 {
    
    public ArrayList<Integer> primalNumbers[];

    private static void loadPrimalNumbers(int max) {
        ArrayList<Integer> primes = new ArrayList();
        primes.add(2);
        for(int i=3;i<=max;i++){
            primes.add(i);
        }
        primalNumbers = primes;
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        loadPrimalNumbers(2000000);
        System.out.println(primalNumbers.toString());
    }
    
}
