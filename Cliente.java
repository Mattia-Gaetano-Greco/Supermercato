import java.util.HashMap;
/* 
* Simulatore di cliente
* @author Mattia Gaetano Greco & Daniele Gherardi
* @version 1.0.0
*/
public class Cliente extends Thread{
    //boolean indica se il prodotto è stato trovato o meno
    private HashMap<Prodotto, Boolean> listaSpesa;
    private static int numero = 0;
    public String nome;
    /**
     * Costruttore di default
     */
    public Cliente(){
        listaSpesa=new HashMap<Prodotto, Boolean>();
        for (int i = 0; i < (int)(Math.random()*3)+1; i++) {
            listaSpesa.put(Supermercato.reparti[(int)((Math.random()-0.001)*5)].getCasualProdotto(), false);
        }
        //nome = new Integer(numero).toString();
        nome = "" + numero;
        numero+=1;
    }
    /**
     * Fa partire il thread del cliente. La vita del cliente si divide in due fasi: fase di ricerca e fase di pagamento.
     * Nella fase di ricerca il cliente, ogni 500 millisecondi, prende un prodotto casuale da un reparto casuale impegnandone il semaforo.
     * Nella fase di pagamento il cliente cerca la cassa con la coda minore e si mette in coda
     */
    @Override
    public void run() {
        // prende i prodotti nella sua lista
        for(Prodotto p : listaSpesa.keySet()){
            compraProdotto(p);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // cerca la cassa con la coda più breve e si accoda in quella cassa
        int cassa = 0;
        int coda = 100;
        for(int i = 0; i < Supermercato.casse.length; i++){
            Cassa c = Supermercato.casse[i];
            if(c.isOpen){
                if(c.getCoda()<coda){
                    coda=c.getCoda();
                    cassa=i;
                }
            }
        }
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Supermercato.casse[cassa].paga(this);
    }
    /** 
     * Prende il prodotto dal reparto (se lo trova)
     * @param p Il prodotto che il cliente cerca
    */
    public void compraProdotto(Prodotto p){
        //se trova il prodotto modifica il value
        //sua listaSpesa
        if(p.compra()) {
            listaSpesa.put(p, true);
            System.out.println("Cliente "+nome+" ha trovato "+p.name);
        } else {
            System.out.println("Cliente "+nome+" non ha trovato "+p.name);
        }
    }
    /**
     * Restituisce la lista della spesa
     * @return Lista della spesa
     */
    public HashMap<Prodotto, Boolean> getListaSpesa(){
        return listaSpesa;
    }
}
