
package businesslogic;

import datalayer.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Casper P, Frederik, Mikkel
 */

public class Pizza {
    
    private int pizzaNummer;
    private String pizzaNavn;
    private double pris;
    
    public Pizza(){
    }
    
    public Pizza(int pizzaNummer, String pizzaNavn, double pris) {
        this.pizzaNummer=pizzaNummer;
        this.pizzaNavn=pizzaNavn;
        this.pris=pris;
    }

    public int getPizzaNummer() {
        return pizzaNummer;
    }

    public String getPizzaNavn() {
        return pizzaNavn;
    }

    public double getPris() {
        return pris;
    }

    public void setPizzaNummer(int pizzaNummer) {
        this.pizzaNummer = pizzaNummer;
    }

    public void setPizzaNavn(String pizzaNavn) {
        this.pizzaNavn = pizzaNavn;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    
    @Override
    public String toString(){
    StringBuilder sb = new StringBuilder(100);
    return sb.append("Pizza: ").append(pizzaNummer)
          .append(", ").append(pizzaNavn)
          .append(", Pris = ").append(pris)
          .toString();
}
    
    
}
