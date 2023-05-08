import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.LinkedList;
import javax.swing.JLabel;
/**
 * Cassa che gestisce una coda di oggetti di tipo Cliente (utilizzando i Semaphore)
 * @author Daniele Gherardi
 * @version 1.0.0
 */
public class Cassa {
    private Semaphore semaforo;
    //private int totGiornaliero;
    public boolean isOpen;
    private LinkedList<Cliente> coda;
    public JLabel[] jlabelCoda;
    /**
     * Crea una cassa con una coda lunga 5 clienti.
     */
    public Cassa(){
        semaforo=new Semaphore(1);
        //totGiornaliero=0;
        isOpen=false;
        coda = new LinkedList<Cliente>();
        jlabelCoda = new JLabel[5];
    }
    /**
     * Apre o chiude la cassa (a seconda dello stato in cui si trova).
     */
    public void apri_chiudiCassa(){
        isOpen=!isOpen;
    }
    /**
     * Blocca la cassa per 2 secondi, durante i quali per ogni prodotto del cliente accredita al supermercato il prezzo dello stesso. Rimuove in seguito il cliente dalla coda.
     * @param cliente Cliente di cui gestire la spesa
     */
    public void paga(Cliente cliente) {
        if(isOpen){
            coda.addLast(cliente);
            scriviCodaSuNuvoletta();
            if(coda.size()>5){
                Supermercato.apriNuovaCassa();
            }
            System.out.println("cliente "+cliente.nome+" è stato aggiunto in coda alla cassa");
            try
            {
                semaforo.acquire();
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }

            HashMap<Prodotto, Boolean> listaTemporanea = cliente.getListaSpesa();
            int totCliente = 0;
            int i = 0;
            Collection<Boolean> comprato = listaTemporanea.values();
            for(Boolean value : comprato){
                Prodotto p = (Prodotto)listaTemporanea.keySet().toArray()[i];
                if(value==true){
                    try
                    {
                        java.util.concurrent.TimeUnit.SECONDS.sleep(2);
                    }
                    catch (InterruptedException ie)
                    {
                        ie.printStackTrace();
                    }
                    //totGiornaliero+=p.price;
                    totCliente+=p.price;
                }
                i++;
            }
            System.out.println("totale pagato da cliente "+cliente.nome+": "+totCliente);
            coda.remove(0);
            scriviCodaSuNuvoletta();
            System.out.println("cliente "+cliente.nome+" rimosso dalla coda");
            semaforo.release();
        }
    }
    /**
     * Scrive i nomi dei clienti in coda (ordinati) sulla nuvoletta della cassa
     */
    private void scriviCodaSuNuvoletta(){
        int max = coda.size() < 5 ? coda.size() : 4;
        int i = 0;
        for (; i < max; i++){
            jlabelCoda[i].setText(coda.get(i).nome);
        }
        for (; i < 5; i++) {
            jlabelCoda[i].setText("");
        }
        if (coda.size() > 4) {
            jlabelCoda[4].setText("+"+(coda.size()-4));
        }
    }
    /**
     * Restituisce la dimensione della coda
     * @return dimensione della coda
     */
    public int getCoda(){
        return coda.size();
    }
}
