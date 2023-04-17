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
        isOpen=false;
    }

    public void apri_chiudiCassa(){
        isOpen=!isOpen;
    }

    public void incassa(Cliente cliente) {
        HashMap<Prodotto, Boolean> temporanea = cliente.getListaSpesa();

        int i = 0;
        Collection<Boolean> values = temporanea.values();
        for(Boolean value : values){
            if(value==true){
                Prodotto p = (Prodotto)temporanea.keySet().toArray()[i];
                totGiornaliero+=p.price;
            }
            i++;
        }
        System.out.println(totGiornaliero + "");
    }
}
