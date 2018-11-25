/*
 * Desenvolvido por Whale(1)
 */
package shedulingpid;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author jonas
 */
public class ShedulingPID {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        graphicScheduler();
    }
    
    public static void graphicScheduler() {
        JFrame frame = new JFrame("Escalonador - Jonas Rafael Duarte dos Santos");
        frame.setSize(800,600);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GraphicScheduler(), BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
}
