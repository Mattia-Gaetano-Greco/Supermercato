import java.util.HashMap;

public class Cliente extends Thread{
    //private LinkedList<Prodotto> listaSpesa;
    
    //boolean indica se il prodotto è stato trovato o meno
    private HashMap<Prodotto, Boolean> listaSpesa;

    public Cliente(){
        listaSpesa=new HashMap<Prodotto, Boolean>();
        listaSpesa.put(new Prodotto("banana", 20), true);
        listaSpesa.put(new Prodotto("carne", 100), false);
        listaSpesa.put(new Prodotto("arancia", 10), true);
    }

    @Override
    public void run() {
        //gira per il negozio cercando i prodotti
        //richiama il metodo compra prodotto
    }

    public void compraProdotto(Prodotto p){
        //se trova il prodotto modifica il value
        //sua listaSpesa
    }

    public HashMap getListaSpesa(){
        return listaSpesa;
    }
}