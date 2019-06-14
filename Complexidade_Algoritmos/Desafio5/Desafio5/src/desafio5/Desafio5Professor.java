
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

/**
 * Desafio 5
 *
 * @author Conan,O Barbaro
 *
 */
public class Desafio5Professor {

    private static int j;
    private static int k;

    private static Vector primos = new Vector();

    public static void main(String[] args) {

        long inicio = System.currentTimeMillis();

        ArrayList lista = new ArrayList();

        primos.add(new Integer(2));
        primos.add(new Integer(3));
        primos.add(new Integer(5));
        primos.add(new Integer(7));
        primos.add(new Integer(11));
        primos.add(new Integer(13));
        primos.add(new Integer(17));
        primos.add(new Integer(19));
        primos.add(new Integer(23));
        primos.add(new Integer(31));
        primos.add(new Integer(37));
        primos.add(new Integer(41));

        String n = JOptionPane.showInputDialog("Informe n (par) ");
        int ene = Integer.parseInt(n);

        for (int i = 1; i <= ene; i++) {
            lista.add(new Integer(i));
        }
        permuta(lista, 0);

        long fim = System.currentTimeMillis();
        System.out.println(new SimpleDateFormat("ss.SSS").format(new Date(fim - inicio)));
    }

    static void permuta(ArrayList arr, int k) {
        boolean passou = true;

        for (int i = k; i < arr.size(); i++) {
            java.util.Collections.swap(arr, i, k);
            permuta(arr, k + 1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            int metade = arr.size() / 2;

            for (int j = 0; j < arr.size(); j++) {
                int um = (int) arr.get(j);
                int dois;
                if (j + 1 == arr.size()) {
                    dois = (int) arr.get(0);
                } else {
                    dois = (int) arr.get(j + 1);
                }
                Integer numteste = new Integer(um + dois);
                if (!primos.contains(numteste)) {
                    passou = false;
                }
            }

            for (int j = 0; j < metade; j++) {
                int um = (int) arr.get(j);
                int dois = (int) arr.get(j + metade);
                Integer numteste = new Integer(um + dois);
                if (!primos.contains(numteste)) {
                    passou = false;
                }
            }
            if (passou) {
                System.out.println(java.util.Arrays.toString(arr.toArray()));
            }
        }
    }
}
