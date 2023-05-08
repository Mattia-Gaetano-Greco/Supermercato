import java.util.HashMap;

public class Cliente extends Thread{
    //boolean indica se il prodotto è stato trovato o meno
    private HashMap<Prodotto, Boolean> listaSpesa;
    private static int numero = 0;
    public String nome;
    
    public Cliente(){
        listaSpesa=new HashMap<Prodotto, Boolean>();
        for (int i = 0; i < (int)(Math.random()*3)+1; i++) {
            listaSpesa.put(Supermercato.reparti[(int)((Math.random()-0.001)*5)].getCasualProdotto(), false);
        }
        //nome = new Integer(numero).toString();
        nome = "" + numero;
        numero+=1;
    }

    @Override
    public void run() {
        //gira per il negozio cercando i prodotti
        //richiama il metodo compra prodotto
        for(Prodotto p : listaSpesa.keySet()){
            compraProdotto(p);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        int cassa = 0; // MODIFICARE CON LA CASSA CON CODA PIù CORTA
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

    public HashMap<Prodotto, Boolean> getListaSpesa(){
        return listaSpesa;
    }
}
