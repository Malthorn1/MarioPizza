package businesslogic;
/**
 *
 * @author Casper P, Frederik, Mikkel
 */

public class Bestilling {

    private Pizza pizza;
    private int ordreNummer;

    public Bestilling(Pizza pizza, int ordreNummer) {
        this.pizza = pizza;
        this.ordreNummer = ordreNummer;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getOrdreNummer() {
        return ordreNummer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        return sb.append("").append(pizza)
                .append(", ordreNummer: ").append(ordreNummer)
                .toString();
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

}
