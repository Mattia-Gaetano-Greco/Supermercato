import java.util.LinkedList;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.*;
/**
 * Simulatore di supermercato con clienti, casse e reparti
 * @author Daniele Gherardi & Mattia Gaetano Greco
 * @version 1.0.0
 */
public class Supermercato{
    //private static int earning;
    //private static boolean isOpen;
    //private static Supermercato supermercato;
    
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
    /**
     * Fa partire la simulazione del supermercato, avviando quindi i thread degli oggetti Cliente e creando i reparti e le casse
     */
    public static void init(){
        //earning=0;
        //isOpen=true;
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
        for(int i = 0; i < 50; i++){
            clienti.add(new Cliente());
        }

        initFinestra();
        for (int i = 0; i < clienti.size(); i++){
            try
            {
                java.util.concurrent.TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
            clienti.get(i).start();
        }
    }
    /**
     * Inizializza la finestra su cui verrà visualizzata la mappa del supermercato su cui sta avvenendo la simulazione
     */
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
            casseGrafica[i]=new JLabel("Cassa!");
            finestra.add(casseGrafica[i]);
            casseGrafica[i].setSize(cassaSize, areaCasse[3] - areaCasse[1]);
            casseGrafica[i].setLocation(areaCasse[0] + cassaSize * i * 2, areaCasse[1]);
            if(i==0){
                casse[i].apri_chiudiCassa();
            }
            if(casse[i].isOpen){
                casseGrafica[i].setBackground(Color.GREEN);    
            }else{
                casseGrafica[i].setBackground(Color.RED);
            }
            casseGrafica[i].setOpaque(true);
            JLabel c = new JLabel();
            finestra.add(c);
            c.setSize(300, 150);
            c.setLocation((int)casseGrafica[i].getLocation().getX() - (int)casseGrafica[i].getSize().getWidth()/2, (int)casseGrafica[i].getLocation().getY() + (int)(casseGrafica[i].getSize().getHeight()));
            c.setVisible(false);
            c.setOpaque(true);
            c.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            Prodotto[] prodottiReparto = reparti[i].getArrayProdotti();
            reparti[i].labelProdotti = new HashMap<Prodotto, JLabel>();
            casseGrafica[i].addMouseListener(new MouseListener() {
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
            for (int j = 0; j < 5; j++){
                casse[i].jlabelCoda[j] = new JLabel();
                c.add(casse[i].jlabelCoda[j]);
                casse[i].jlabelCoda[j].setSize(c.getWidth() - 10, c.getHeight()/prodottiReparto.length);
                casse[i].jlabelCoda[j].setLocation(10, casse[i].jlabelCoda[j].getHeight()*j);
            }
        }
        // creazione scaffali
        scaffali = new JLabel[reparti.length];
        int scaffaleSize = (areaScaffali[2] - areaScaffali[0]) / 10;
        for (int i = 0; i < scaffali.length; i++){
            JLabel l = new JLabel(reparti[i].nomeReparto);
            finestra.add(l);
            l.setSize(scaffaleSize, areaScaffali[3] - areaScaffali[1]);
            l.setLocation(areaScaffali[0] + scaffaleSize * i * 2, areaScaffali[1]);
            l.setBackground(Color.YELLOW);
            l.setOpaque(true);
            // creazione "nuvoletta"
            JLabel c = new JLabel();
            finestra.add(c);
            c.setSize(300, 150);
            c.setLocation((int)l.getLocation().getX() - (int)l.getSize().getWidth()/2, (int)l.getLocation().getY() - 150);
            c.setVisible(false);
            c.setOpaque(true);
            c.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            Prodotto[] prodottiReparto = reparti[i].getArrayProdotti();
            reparti[i].labelProdotti = new HashMap<Prodotto, JLabel>();
            // creazione label prodotti
            for (int j = 0; j < prodottiReparto.length; j++) {
                JLabel p = new JLabel(prodottiReparto[j].name+"    "+reparti[i].getQuantity(prodottiReparto[j]));
                c.add(p);
                p.setSize(c.getWidth() - 10, c.getHeight()/prodottiReparto.length);
                p.setLocation(10, p.getHeight()*j);
                reparti[i].labelProdotti.put(prodottiReparto[j], p);
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
    /**
     * Apre una cassa
     */
    public static void apriNuovaCassa() {
        for(int i = 0; i < casse.length; i++){
            if(!casse[i].isOpen){
                casse[i].apri_chiudiCassa();
                casseGrafica[i].setBackground(Color.GREEN);
                return;
            }
        }
    }
}
