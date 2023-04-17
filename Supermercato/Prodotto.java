public class Prodotto {
    public int price;
    public String name;
    public Reparto reparto;


    public Prodotto(String name, int price, Reparto reparto){
        this.name = name;
        this.price = price;
        this.reparto = reparto;
    }
    
    public boolean compra(){
        return reparto.prendi(this);
    }
}
