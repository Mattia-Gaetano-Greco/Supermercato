import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Cassa {
    private Semaphore semaforo;
    private int totGiornaliero;
    private boolean isOpen;

    public Cassa(){
        semaforo=new Semaphore(1);
        totGiornaliero=0;
        isOpen=true;
    }

    public void apri_chiudiCassa(){
        isOpen=!isOpen;
    }

    public void incassa(Cliente cliente) {
        if(isOpen){
            try
            {
                semaforo.acquire();
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }

            HashMap<Prodotto, Boolean> temporanea = cliente.getListaSpesa();
            int totCliente = 0;
            int i = 0;
            Collection<Boolean> values = temporanea.values();
            for(Boolean value : values){
                if(value==true){
                    Prodotto p = (Prodotto)temporanea.keySet().toArray()[i];
                    totGiornaliero+=p.price;
                    totCliente+=p.price;
                }
                i++;
            }
            System.out.println(totCliente);
            semaforo.release();
        }
    }
}
