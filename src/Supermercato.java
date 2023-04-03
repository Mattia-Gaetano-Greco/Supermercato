import java.util.LinkedList;

/**
 * Supermercato
 */
public class Supermercato {

    private int earning;
    private boolean isOpen;

    private Cassa[] casse;
    private LinkedList<Cliente> clienti;
    private Reparto[] reparti;

    public Supermercato(){
        earning=0;
        isOpen=true;

        casse=new Cassa[3];
        clienti=new LinkedList<Cliente>();
        reparti=new Reparto[3];
    }
}