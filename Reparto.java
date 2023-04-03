import java.util.HashMap;

public class Reparto {
    private HashMap <Prodotto, Integer> prodotti;
    public String nomeReparto;

    public Reparto(String nomeReparto){
        
        prodotti=new HashMap<Prodotto, Integer>();
        this.nomeReparto = nomeReparto;
        prodotti.put(new Prodotto("banana", 20, this), 2);
        prodotti.put(new Prodotto("arancio", 10, this), 2);
        prodotti.put(new Prodotto("kiwi", 10, this), 2);
        prodotti.put(new Prodotto("pera", 10, this), 2);
        prodotti.put(new Prodotto("mela", 5, this), 2);
    }

    //per creare la lista casuale dei clienti
    //come se fosse un volantino di prodotti
    public Prodotto getCasualProdotto(){
        
        int index = (int)(Math.random() * 4);
        Prodotto p = (Prodotto)prodotti.keySet().toArray()[index];
        return p;
    }

    public boolean prendi(Prodotto prodotto){
        if(prodotti.get(prodotto) == 0){
            return false;
        }else{
            prodotti.put(prodotto, prodotti.get(prodotto) - 1);
            return true;
        }
    }
}
