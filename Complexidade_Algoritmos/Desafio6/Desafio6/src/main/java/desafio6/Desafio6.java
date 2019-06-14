/*
 * Desafio6
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package desafio6;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Duarte
 */
public class Desafio6 {

    class Hamburguers extends Exception {

        private int hamburguers;
        private int tempoCeva;

        public Hamburguers(int hamburguers, int tempoCeva) {
            this.hamburguers = hamburguers;
            this.tempoCeva = tempoCeva;
        }

        public int getHamburguers() {
            return hamburguers;
        }

        public int getTempoCeva() {
            return tempoCeva;
        }

        @Override
        public String toString() {
            return "Homer comeu " + hamburguers + " hamburguers e bebeu cerveja por " + tempoCeva + " minutos!";
        }

    }

    public void contaHamburguers(int n, int m, int t) throws Hamburguers {
        if (t < 0) {
            throw new Hamburguers(0, t);
        }
        if (t == 0) {
            throw new Hamburguers(0, 0);
        }
        int h;
        int t1 = -1;
        Hamburguers h1 = new Hamburguers(0, 0);
        h = n < m ? n : m;
        try {
            contaHamburguers(n, m, t - h);
        } catch (Hamburguers hEx) {
            h1 = hEx;
        }
        int t2 = -1;
        Hamburguers h2 = new Hamburguers(0, 0);
        h = n > m ? n : m;
        try {
            contaHamburguers(n, m, t - h);
        } catch (Hamburguers hEx) {
            h2 = hEx;
        }
        if (h1.getTempoCeva() <= h2.getTempoCeva()) {
            if (h1.getTempoCeva() < 0) {
                throw new Hamburguers(h1.getHamburguers(), t);
            } else {
                throw new Hamburguers(h1.getHamburguers() + 1, h1.getTempoCeva());
            }

        } else {
            if (h2.getTempoCeva() < 0) {
                throw new Hamburguers(h2.getHamburguers(), t);
            } else {
                throw new Hamburguers(h2.getHamburguers() + 1, h2.getTempoCeva());
            }
        }
    }

    public void run() {
        long inicio = System.currentTimeMillis();

        try {
            contaHamburguers(3, 5, 4);
        } catch (Hamburguers hEx) {
            System.out.println(hEx.toString());
        }
        //contaHamburguers(3, 5, 7);

        long fim = System.currentTimeMillis();
        System.out.println(new SimpleDateFormat("ss.SSS").format(new Date(fim - inicio)));
    }

    public static void main(String[] args) {
        new Desafio6().run();
    }

}
