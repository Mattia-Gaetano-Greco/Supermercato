import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.LinkedList;
import javax.swing.JLabel;

public class Cassa {
    private Semaphore semaforo;
    //private int totGiornaliero;
    public boolean isOpen;
    private LinkedList<Cliente> coda;
    public JLabel[] jlabelCoda;

    public Cassa(){
        semaforo=new Semaphore(1);
        //totGiornaliero=0;
        isOpen=false;
        coda = new LinkedList<Cliente>();
        jlabelCoda = new JLabel[5];
    }

    public void apri_chiudiCassa(){
        isOpen=!isOpen;
    }

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
    
    private void scriviCodaSuNuvoletta(){
        int max = coda.size() < 5 ? coda.size() : 4;
        int i = 0;
        for (; i < max; i++){
            jlabelCoda[i].setText(coda.get(i).nome);
        }
        for (; i < 5; i++) {
            jlabelCoda[i].setText("");
        }
        if (coda.size() > 5) {
            jlabelCoda[4].setText("+"+(coda.size()-4));
        }
    }

    public int getCoda(){
        return coda.size();
    }
}
