import java.util.HashMap;
import java.util.concurrent.Semaphore;
import javax.swing.*;

public class Reparto {
    private HashMap <Prodotto, Integer> prodotti;
    public String nomeReparto;
    public HashMap<Prodotto, JLabel> labelProdotti;
    private Semaphore semaforo;    

    public Reparto(int numeroReparto){
        semaforo = new Semaphore(1);
        prodotti=new HashMap<Prodotto, Integer>();
        switch(numeroReparto){
            default:
                this.nomeReparto = "frutta";
                prodotti.put(new Prodotto("banana", 5, this), 5);
                prodotti.put(new Prodotto("arancia", 7, this), 5);
                prodotti.put(new Prodotto("kiwi", 10, this), 5);
                prodotti.put(new Prodotto("pera", 3, this), 5);
                prodotti.put(new Prodotto("mela", 2, this), 5);
            break;
            case 1:
                this.nomeReparto = "latticini";
                prodotti.put(new Prodotto("latte", 4, this), 5);
                prodotti.put(new Prodotto("mozzarella", 6, this), 5);
                prodotti.put(new Prodotto("bufala", 9, this), 5);
                prodotti.put(new Prodotto("formaggio", 13, this), 5);
                prodotti.put(new Prodotto("altri latticini", 5, this), 5);
            break;
            case 2:
                this.nomeReparto = "carne";
                prodotti.put(new Prodotto("costine", 12, this), 5);
                prodotti.put(new Prodotto("pollo", 7, this), 5);
                prodotti.put(new Prodotto("maiale", 19, this), 5);
                prodotti.put(new Prodotto("costata", 33, this), 5);
                prodotti.put(new Prodotto("salsiccia", 9, this), 5);
            break;
            case 3:
                this.nomeReparto = "surgelati";
                prodotti.put(new Prodotto("gelati", 5, this), 5);
                prodotti.put(new Prodotto("pizza", 8, this), 5);
                prodotti.put(new Prodotto("patatine", 3, this), 5);
                prodotti.put(new Prodotto("bastoncini", 4, this), 5);
                prodotti.put(new Prodotto("cheescake", 12, this), 5);
            break;
            case 4:
                this.nomeReparto = "bibite";
                prodotti.put(new Prodotto("coca cola", 6, this), 5);
                prodotti.put(new Prodotto("acqua", 1, this), 5);
                prodotti.put(new Prodotto("sprite", 3, this), 5);
                prodotti.put(new Prodotto("fanta", 3, this), 5);
                prodotti.put(new Prodotto("succo", 2, this), 5);
            break;
        }
                
    }

    //per creare la lista casuale dei clienti
    //come se fosse un volantino di prodotti
    public Prodotto getCasualProdotto(){
        int index = (int)((Math.random()-0.001) * 5);
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
            labelProdotti.get(prodotto).setText(prodotto.name+"    "+prodotti.get(prodotto));
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
