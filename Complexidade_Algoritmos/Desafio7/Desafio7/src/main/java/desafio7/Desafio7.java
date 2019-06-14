/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio7;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jonas
 */
public class Desafio7 {

    class Estruturas {

        Map<String, Object[]> estruturas;

        Estruturas() {
            this.estruturas = new HashMap<>();
        }

        public Estruturas add(String nome, Object... estrutura) {
            this.estruturas.put(nome, estrutura);
            return this;
        }

        public Estruturas run() {
            this.estruturas.forEach((String k, Object[] v) -> {
                for (int i = 0; i < v.length; i++) {
                    if (v[i] instanceof String) {
                        v[i] = this.estruturas.get(v[i]);
                    }
                }
            });
            return this;
        }

        public int getPeso(Object[] o) {
            int peso = 0;
            for (int i = 0; i < o.length; i++) {
                if (o[i] instanceof Integer) {
                    peso += (Integer) o[i];
                } else {
                    peso += getPeso((Object[]) o[i]);
                }
            }
            return peso;
        }

        public void isEquilibrado() {
            int peso, pi, pf;
            boolean equilibrado = true;
            Object[] o;
            for (String key : this.estruturas.keySet()) {
                o = this.estruturas.get(key);
                pi = 0;
                pf = 0;
                for (int i = 0; i < o.length; i++) {
                    if (o[i] instanceof Integer) {
                        peso = (Integer) o[i];
                    } else {
                        peso = getPeso((Object[]) o[i]);
                    }
                    if (i < (o.length / 2)) {
                        pi += peso;
                    }
                    if (i > (o.length / 2)) {
                        pf += peso;
                    }
                }
                System.out.println(key + "->" + (pi == pf));
                if (pi != pf) {
                    equilibrado = false;
                }
            }
            System.out.println("Geral->" + equilibrado);
        }

        @Override
        public String toString() {
            return "Estruturas{" + "estruturas=" + estruturas + '}';
        }
    }

    private void run() {
        long inicio = System.currentTimeMillis();

        new Estruturas()
                .add("A", 6, "B", 7, "C", "D")
                .add("B", 9, 11, 8)
                .add("C", 5, 12, 5)
                .add("D", 2, 4, 3)
                .run()
                .isEquilibrado();

        new Estruturas()
                .add("A", 5, "B", 7, "C", "D")
                .add("B", 8, 9, 8)
                .add("C", 5, 12, 5)
                .add("D", 3, 2, 3)
                .run()
                .isEquilibrado();

        long fim = System.currentTimeMillis();
        System.out.println(new SimpleDateFormat("ss.SSS").format(new Date(fim - inicio)));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Desafio7().run();
    }

}
