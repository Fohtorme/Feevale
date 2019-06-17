/*
 * Desafio6
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package desafio6;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Duarte
 */
public class Desafio6 {

    public int[] getGreatestCombination(int idealValue, int... initialValues) {

        int[] values = new int[idealValue + 1];

        int i1;
        int i2;

        for (i1 = 0; i1 < initialValues.length; i1++) {
            values[initialValues[i1]] = 1;
        }

        for (i1 = 1; i1 <= idealValue; i1++) {
            if (values[i1] == 0) {
                continue;
            }
            for (i2 = i1; i2 <= idealValue; i2++) {
                if (values[i2] == 0) {
                    continue;
                }
                if ((i1 + i2) > idealValue) {
                    break;
                }
                if (values[i1 + i2] < (values[i1] + values[i2])) {
                    values[i1 + i2] = values[i1] + values[i2];
                }
            }
        }

        for (i1 = idealValue; i1 >= 0; i1--) {
            if (values[i1] != 0) {
                return new int[]{i1, values[i1], idealValue - i1};
            }
        }

        return new int[]{0, 0, idealValue};
    }

    public void formatToSimpsons(int[] values) {
        if (values[2] == 0) {
            System.out.printf("Homer comeu %d hamburguers e Marge ficou muito contente por ele ter voltado para casa sem beber nada!\n", values[1]);
        } else {
            System.out.printf("Homer comeu %d hamburguers e ainda sobrou %d minutos para encher a cara de ceva!\n", values[1], values[2]);
        }
    }

    public void run() {
        long inicio = System.currentTimeMillis();

        formatToSimpsons(getGreatestCombination(10, 2, 5));
        formatToSimpsons(getGreatestCombination(54, 3, 5));
        formatToSimpsons(getGreatestCombination(55, 3, 5));
        formatToSimpsons(getGreatestCombination(10, 4, 7));
        formatToSimpsons(getGreatestCombination(12, 3, 4));

        long fim = System.currentTimeMillis();
        System.out.println(new SimpleDateFormat("ss.SSS").format(new Date(fim - inicio)));
    }

    public static void main(String[] args) {
        new Desafio6().run();
    }

}
