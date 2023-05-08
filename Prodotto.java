/**
 * Unità di prodotto del supermercato
 * @author Daniele Gherardi
 * @version 1.0.0
 */
public class Prodotto {
    public int price;
    public String name;
    public Reparto reparto;
    /**
     * Costruttore che crea un prodotto
     * @param name Nome del prodotto
     * @param price Prezzo del prodotto
     * @param reparto Reparto di appartenenza del prodotto
     */
    public Prodotto(String name, int price, Reparto reparto){
        this.name = name;
        this.price = price;
        this.reparto = reparto;
    }
    /**
     * Restituisce la quantità del prodotto nel reparto 
     * @return quantità di prodotto nel reparto
     */
    public boolean compra(){
        return reparto.prendi(this);
    }
}
