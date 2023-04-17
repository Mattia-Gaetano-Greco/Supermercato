import java.util.LinkedList;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
/**
 * Supermercato
 */
public class Supermercato /*extends Thread*/{

    private static int earning;
    private static boolean isOpen;
    private static Supermercato supermercato;

    // variabili di Supermercato
    public static boolean isGiocando;
    public static int timerUpdate = 2000;

    // backend
    public static Cassa[] casse;
    private static LinkedList<Cliente> clienti;
    public static Reparto[] reparti;

    // grafica
    public static JFrame finestra = new JFrame();
    private static JLabel[] casseGrafica;
    private static int[] areaCasse = new int[]{700, 50, 1900, 300};
    private static JLabel[] scaffali;
    private static int[] areaScaffali = new int[]{250, 450, 1920, 800};

    public static void init(){
        earning=0;
        isOpen=true;
        //creazione casse
        casse=new Cassa[3];
        for(int i = 0; i < casse.length; i++){
            casse[i] = new Cassa();
        }
        
        //creazione dei reparti del supermercato
        reparti=new Reparto[5];
        for(int i = 0; i < reparti.length; i++){
            reparti[i] = new Reparto(i);
        }
        
        //creazione clienti del supermercato
        clienti=new LinkedList<Cliente>();
        for(int i = 0; i < 10; i++){
            clienti.add(new Cliente());
        }
        System.out.println("passo");
        
        initFinestra();
    }

    public static void initFinestra() {
        // creazione finestra
        Supermercato.finestra.setSize(1920,1040);
        Supermercato.finestra.setResizable(false);
        Supermercato.finestra.setVisible(true);
        Supermercato.finestra.setLayout(null);
        // creazione casse
        casseGrafica = new JLabel[casse.length];
        int cassaSize = (areaCasse[2] - areaCasse[0]) / 10;
        for (int i = 0; i < casseGrafica.length; i++) {
            JLabel l = new JLabel("Cassa!");
            finestra.add(l);
            l.setSize(cassaSize, areaCasse[3] - areaCasse[1]);
            l.setLocation(areaCasse[0] + cassaSize * i * 2, areaCasse[1]);
            l.setBackground(Color.YELLOW);
            l.setOpaque(true);
        }
        // creazione scaffali
        scaffali = new JLabel[reparti.length];
        int scaffaleSize = (areaScaffali[2] - areaScaffali[0]) / 10;
        for (int i = 0; i < scaffali.length; i++){
            JLabel l = new JLabel("Scaffale!");
            finestra.add(l);
            l.setSize(scaffaleSize, areaScaffali[3] - areaScaffali[1]);
            l.setLocation(areaScaffali[0] + scaffaleSize * i * 2, areaScaffali[1]);
            l.setBackground(Color.GREEN);
            l.setOpaque(true);
            JLabel c = new JLabel();
            finestra.add(c);
            c.setSize(300, 150);
            c.setLocation((int)l.getLocation().getX() - (int)l.getSize().getWidth()/2, (int)l.getLocation().getY() - 150);
            c.setVisible(false);
            c.setOpaque(true);
            c.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            Prodotto[] prodottiReparto = reparti[i].getArrayProdotti();
            for (int j = 0; j < prodottiReparto.length; j++) {
                JLabel p = new JLabel(prodottiReparto[j].name+"    "+reparti[i].getQuantity(prodottiReparto[j]));
                c.add(p);
                p.setSize(c.getWidth() - 10, c.getHeight()/prodottiReparto.length);
                p.setLocation(10, p.getHeight()*j);
            }
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
    }


    public void prova() {
        clienti.get(0).start();
    }


    /* 
    @Override
    public void run() {
        clienti.get(0).start();
    }
    */
    
}