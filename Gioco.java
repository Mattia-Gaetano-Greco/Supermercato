import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
//import java.awt.*;

public class Gioco {

    public static boolean isGiocando;
    public static int timerUpdate = 2000;
    public static JFrame finestra = new JFrame();
    private static JLabel[] casse = new JLabel[5];
    private static int[] areaCasse = new int[]{700, 50, 1900, 300};
    private static JLabel[] scaffali = new JLabel[5];
    private static int[] areaScaffali = new int[]{250, 450, 1920, 800};

    public static void init() {
        // creazione finestra
        Gioco.finestra.setSize(1920,1040);
        Gioco.finestra.setResizable(false);
        Gioco.finestra.setVisible(true);
        Gioco.finestra.setLayout(null);
        // creazione casse
        int cassaSize = (areaCasse[2] - areaCasse[0]) / 10;
        for (int i = 0; i < casse.length; i++) {
            JLabel l = new JLabel("Cassa!");
            finestra.add(l);
            l.setSize(cassaSize, areaCasse[3] - areaCasse[1]);
            l.setLocation(areaCasse[0] + cassaSize * i * 2, areaCasse[1]);
            l.setBackground(Color.YELLOW);
            l.setOpaque(true);
        }
        // creazione scaffali
        int scaffaleSize = (areaScaffali[2] - areaScaffali[0]) / 10;
        for (int i = 0; i < scaffali.length; i++){
            JLabel l = new JLabel("Scaffale!");
            finestra.add(l);
            l.setSize(scaffaleSize, areaScaffali[3] - areaScaffali[1]);
            l.setLocation(areaScaffali[0] + scaffaleSize * i * 2, areaScaffali[1]);
            l.setBackground(Color.GREEN);
            l.setOpaque(true);
            JLabel c = new JLabel("Tutti i prodotti!");
            finestra.add(c);
            c.setSize(300, 150);
            c.setLocation((int)l.getLocation().getX() - (int)l.getSize().getWidth()/2, (int)l.getLocation().getY() - 150);
            c.setVisible(false);
            c.setOpaque(true);
            c.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            l.addMouseListener(new MouseListener() {
                public void mouseExited(MouseEvent m){
                    c.setVisible(false);
                }
                public void mouseReleased(MouseEvent m){}
                public void mouseClicked(MouseEvent m){}
                public void mousePressed(MouseEvent m){}
                public void mouseEntered(MouseEvent m){
                    c.setVisible(true);
                }
            });
        }
        // creazione supermercato
        new Supermercato();
        for (int i = 0; i < 10; i++) {
            new Cliente();
        }
    }
}