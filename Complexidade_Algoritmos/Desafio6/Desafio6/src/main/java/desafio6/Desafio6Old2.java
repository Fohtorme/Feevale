/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio6;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author jonas
 */
public class Desafio6Old2 {

//    class CervejaException extends Exception {
//
//        int tempo;
//
//        public void subtractTempo(int tempo) {
//            this.tempo -= tempo;
//        }
//
//        @Override
//        public String toString() {
//            return "Homer bebeu cerveja por " + tempo + " minutos!";
//        }
//
//        private CervejaException(int tempo, String string) {
//            super(string);
//        }
//
//    }
//
//    class HamburguerException extends Exception {
//
//        int h;
//
//        public int getH() {
//            return h;
//        }
//
//        private HamburguerException(int h, String string) {
//            super(string);
//        }
//
//    }
//
//    private int xecaMelhorCombinacao(int th1, int th2, int tTotal) throws Exception {
//        if (tTotal == 0) {
//            return 0;
//        }
//        int t = th1 < th2 ? th1 : th2;
//        if (tTotal % t == 0) {
//            return tTotal / t;
//        }
//        if ((tTotal - t) < t) {
//            throw new CervejaException(tTotal, "");
//        }
//
//        int burgers = 0;
//        int tAux = th1 > th2 ? th1 : th2;
//        try {
//            burgers = xecaMelhorCombinacao(th1, th2, tTotal - t);
//        } catch (Exception ex) {
//            try {
//                burgers = xecaMelhorCombinacao(th1, th2, tTotal - tAux);;
//            } catch (Exception ex2) {
//                try {
//                    burgers = xecaMelhorCombinacao(th1, th2, tTotal - t);
//                } catch (HamburguerException ex3) {
//                    throw new HamburguerException(ex3.getH() + 1, ex3.toString());
//                } catch (CervejaException ex3) {
//                    ex3.subtractTempo(t);
//                    throw new HamburguerException(1, ex3.toString());
//                }
//            }
//        }
//        return 1 + burgers;
//    }
//
//    private String contaHamburguers(int th1, int th2, int tTotal) {
//        try {
//            return "Homer comeu " + xecaMelhorCombinacao(th1, th2, tTotal) + " hamburguers e voltou sóbrio para casa!!!";
//        } catch (Exception ex) {
//            return ex.toString();
//        }
//    }
//
//    private void run() {
//        long inicio = System.currentTimeMillis();
//
////        System.out.println(contaHamburguers(3, 5, 54));
////        System.out.println(contaHamburguers(3, 5, 55));
////        System.out.println(contaHamburguers(3, 5, 56));
////        System.out.println(contaHamburguers(3, 5, 57));
////        System.out.println(contaHamburguers(3, 5, 2));
//        System.out.println(contaHamburguers(3, 5, 4));
////        System.out.println(contaHamburguers(3, 9, 18));
////        System.out.println(contaHamburguers(3, 9, 15));
////        System.out.println(contaHamburguers(3, 9, 17));
//
//        long fim = System.currentTimeMillis();
//        System.out.println(new SimpleDateFormat("ss.SSS").format(new Date(fim - inicio)));
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        new Desafio6().run();
    }
}
