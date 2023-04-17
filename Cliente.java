import java.util.HashMap;

public class Cliente extends Thread{
    //private LinkedList<Prodotto> listaSpesa;
    
    //boolean indica se il prodotto è stato trovato o meno
    private HashMap<Prodotto, Boolean> listaSpesa;
    //===============================
    public Cliente(){
        listaSpesa=new HashMap<Prodotto, Boolean>();
        listaSpesa.put(Supermercato.reparti[/*x*/ 0 ].getCasualProdotto(), false);
        listaSpesa.put(Supermercato.reparti[/*x*/ 0 ].getCasualProdotto(), false);
        listaSpesa.put(Supermercato.reparti[/*x*/ 0 ].getCasualProdotto(), false);
        /*listaSpesa.put(new Prodotto("banana", 20), true);
        listaSpesa.put(new Prodotto("carne", 100), false);
        listaSpesa.put(new Prodotto("arancia", 10), true);*/
    }

    @Override
    public void run() {
        
        //gira per il negozio cercando i prodotti
        //richiama il metodo compra prodotto
        for(Prodotto p : listaSpesa.keySet()){
            compraProdotto(p);
        }
        Supermercato.casse[0].incassa(this);
    }

    public void compraProdotto(Prodotto p){
        //se trova il prodotto modifica il value
        //sua listaSpesa
        if(p.compra())
            listaSpesa.put(p, true);
    }

    public HashMap<Prodotto, Boolean> getListaSpesa(){
        return listaSpesa;
    }
}
