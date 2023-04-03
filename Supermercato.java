import java.util.LinkedList;

/**
 * Supermercato
 */
public class Supermercato /*extends Thread*/{

    private int earning;
    private boolean isOpen;

    public Cassa[] casse;
    private LinkedList<Cliente> clienti;
    public Reparto[] reparti;

    public Supermercato(){
        
        earning=0;
        isOpen=true;
        //creazione casse
        casse=new Cassa[3];
        for(int i = 0; i < casse.length; i++){
            casse[i] = new Cassa();
        }
        
        //creazione dei reparti del supermercato
        reparti=new Reparto[3];
        for(int i = 0; i < reparti.length; i++){
            reparti[i] = new Reparto("reparto: " + i);
        }
        
        //creazione clienti del supermercato
        clienti=new LinkedList<Cliente>();
        for(int i = 0; i < 10; i++){
            clienti.add(new Cliente(this));
        }
        System.out.println("passo");
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