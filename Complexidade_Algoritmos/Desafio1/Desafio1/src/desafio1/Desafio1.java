/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio1;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author jonas
 */
public class Desafio1 {

    // Convert a base 10 number to the specified base
    public static String numberToBaseN(long number, long base, String[] symbols) {
        long val = number / base;
        int remainder = (int) (number % base);
        if (val == 0) {
            return convertNumberToSymbol(remainder, symbols);
        }
        return "" + numberToBaseN(val, base, symbols) + convertNumberToSymbol(remainder, symbols);
    }

    // Convert number to symbol
    public static String convertNumberToSymbol(int number, String symbols[]) {
        return symbols[number];
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long inicio = System.currentTimeMillis();

        DecimalFormat dfmt = new DecimalFormat("0");
        System.out.println("" + numberToBaseN(
                1000000,
                7,
                new String[]{"0", "1", "2", "5", "6", "8", "9"}));

        long fim = System.currentTimeMillis();
        System.out.println(new SimpleDateFormat("ss.SSS").format(new Date(fim - inicio)));
    }

}
