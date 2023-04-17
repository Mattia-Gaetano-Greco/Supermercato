import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Reparto {
    private HashMap <Prodotto, Integer> prodotti;
    public String nomeReparto;

    private Semaphore semaforo;    

    public Reparto(int numeroReparto){
        semaforo = new Semaphore(1);
        prodotti=new HashMap<Prodotto, Integer>();
        switch(numeroReparto){
            default:
                this.nomeReparto = "frutta";
                prodotti.put(new Prodotto("banana", 10, this), 2);
                prodotti.put(new Prodotto("arancio", 10, this), 2);
                prodotti.put(new Prodotto("kiwi", 10, this), 2);
                prodotti.put(new Prodotto("pera", 10, this), 2);
                prodotti.put(new Prodotto("mela", 10, this), 2);
            break;
            case 1:
                this.nomeReparto = "latticini";
                prodotti.put(new Prodotto("latte", 20, this), 2);
                prodotti.put(new Prodotto("mozzarella", 10, this), 2);
                prodotti.put(new Prodotto("bufala", 10, this), 2);
                prodotti.put(new Prodotto("formaggio", 10, this), 2);
                prodotti.put(new Prodotto("altri latticini", 5, this), 2);
            break;
            case 2:
                this.nomeReparto = "carne";
                prodotti.put(new Prodotto("costine", 20, this), 2);
                prodotti.put(new Prodotto("pollo", 10, this), 2);
                prodotti.put(new Prodotto("maiale", 10, this), 2);
                prodotti.put(new Prodotto("pera", 10, this), 2);
                prodotti.put(new Prodotto("mela", 5, this), 2);
            break;
            case 3:
                this.nomeReparto = "surgelati";
                prodotti.put(new Prodotto("banana", 20, this), 2);
                prodotti.put(new Prodotto("arancio", 10, this), 2);
                prodotti.put(new Prodotto("kiwi", 10, this), 2);
                prodotti.put(new Prodotto("pera", 10, this), 2);
                prodotti.put(new Prodotto("mela", 5, this), 2);
            break;
            case 4:
                this.nomeReparto = "bibite";
                prodotti.put(new Prodotto("banana", 20, this), 2);
                prodotti.put(new Prodotto("arancio", 10, this), 2);
                prodotti.put(new Prodotto("kiwi", 10, this), 2);
                prodotti.put(new Prodotto("pera", 10, this), 2);
                prodotti.put(new Prodotto("mela", 5, this), 2);
            break;
        }
                
    }

    //per creare la lista casuale dei clienti
    //come se fosse un volantino di prodotti
    public Prodotto getCasualProdotto(){
        
        int index = (int)(Math.random() * 4);
        Prodotto p = (Prodotto)prodotti.keySet().toArray()[index];
        return p;
    }

    //cliente prende il prodotto
    public boolean prendi(Prodotto prodotto){

        try
        {
            semaforo.acquire();
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }

        if(prodotti.get(prodotto) == 0){
            semaforo.release();
            return false;
        }else{
            prodotti.put(prodotto, prodotti.get(prodotto) - 1);
            semaforo.release();
            return true;
        }
    }
    
    public Prodotto[] getArrayProdotti(){
        return prodotti.keySet().toArray(new Prodotto[5]);
    }

    public int getQuantity(Prodotto prod){
        return prodotti.get(prod);
    }
    
}
