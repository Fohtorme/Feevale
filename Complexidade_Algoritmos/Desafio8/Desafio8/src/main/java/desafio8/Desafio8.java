/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio8;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author jonas
 */
public class Desafio8 {

    class RetanguloSimples {

        private int x1;
        private int y1;
        private int x2;
        private int y2;

        public RetanguloSimples(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public int getX1() {
            return x1;
        }

        public int getY1() {
            return y1;
        }

        public int getX2() {
            return x2;
        }

        public int getY2() {
            return y2;
        }

        @Override
        public String toString() {
            return "RetanguloSimples{" + "x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + '}';
        }

    }

    class RetanguloComplexo {

        private int xOffset;
        private int yOffset;
        private boolean[][] retangle;

        public RetanguloComplexo(int x1, int y1, int x2, int y2) {
            this.xOffset = x1;
            this.yOffset = y1;
            this.retangle = new boolean[x2 - x1][y2 - y1];
        }

        public void comeRetangulo(RetanguloSimples rs) {
            int xi = rs.getX1() - xOffset;
            int yi = rs.getY1() - yOffset;
            int xf = rs.getX2() - xOffset - 1;
            int yf = rs.getY2() - yOffset - 1;

            if (xi > retangle.length
                    || yi > retangle[0].length
                    || xf < 0
                    || yf < 0) {
                return;
            }

            if (xi < 0) {
                xi = 0;
            }
            if (yi < 0) {
                yi = 0;
            }
            if (xf >= retangle.length) {
                xf = retangle.length - 1;
            }
            if (yf >= retangle[0].length) {
                yf = retangle[0].length - 1;
            }

            for (int i = xi; i <= xf; i++) {
                for (int j = yi; j <= yf; j++) {
                    retangle[i][j] = true;
                }
            }
        }

        public boolean isVisivel() {
            for (int i = 0; i < retangle.length; i++) {
                for (int j = 0; j < retangle[i].length; j++) {
                    if (!retangle[i][j]) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "RetanguloComplexo{" + "xOffset=" + xOffset + ", yOffset=" + yOffset + ", retangle=" + retangle.length + "x" + retangle[0].length + '}';
        }
    }

    // Não seguiu um padrão bom de nomenclatura pq eu quiz fazer assim
    private boolean veSeDaPraPintarORetangulo(
            RetanguloComplexo rc,
            RetanguloSimples... rs) {
        for (int i = 0; i < rs.length; i++) {
            rc.comeRetangulo(rs[i]);
        }
        return rc.isVisivel();
    }

    private void run() {
        long inicio = System.currentTimeMillis();

        System.out.println(veSeDaPraPintarORetangulo(
                new RetanguloComplexo(5, 3, 12, 10),
                new RetanguloSimples(1, 7, 8, 14),
                new RetanguloSimples(7, 5, 11, 13),
                new RetanguloSimples(10, 7, 17, 14),
                new RetanguloSimples(9, 1, 17, 6)));

        System.out.println(veSeDaPraPintarORetangulo(
                new RetanguloComplexo(5, 3, 12, 10),
                new RetanguloSimples(5, 3, 12, 10)));

        System.out.println(veSeDaPraPintarORetangulo(
                new RetanguloComplexo(5, 3, 12, 10),
                new RetanguloSimples(5, 3, 12, 11)));

        System.out.println(veSeDaPraPintarORetangulo(
                new RetanguloComplexo(5, 3, 12, 11),
                new RetanguloSimples(5, 3, 12, 10)));

        System.out.println(veSeDaPraPintarORetangulo(
                new RetanguloComplexo(1, 1, 20000, 20000),
                new RetanguloSimples(1, 1, 10000, 10000),
                new RetanguloSimples(10000, 1, 20000, 10000),
                new RetanguloSimples(1, 10000, 10000, 20000),
                new RetanguloSimples(10000, 10000, 20000, 20000)));
        

        System.out.println(veSeDaPraPintarORetangulo(
                new RetanguloComplexo(1, 1, 20000, 20000),
                new RetanguloSimples(1, 1, 10000, 10000),
                new RetanguloSimples(10000, 1, 20000, 10000),
                new RetanguloSimples(1, 10000, 10000, 20000),
                new RetanguloSimples(10000, 10000, 20000, 19999)));

        long fim = System.currentTimeMillis();
        System.out.println(new SimpleDateFormat("ss.SSS").format(new Date(fim - inicio)));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Desafio8().run();
    }

}
