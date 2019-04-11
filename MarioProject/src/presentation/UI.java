package presentation;

import businesslogic.Bestilling;
import businesslogic.Pizza;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Casper P, Frederik, Mikkel
 */


public interface UI {

    public int vælgPizza();
    
    public void vælgPizzaDB();
    
    public void visAktiveOrdreDB() throws SQLException;
            
    public void visPizzaValg(String string);

    public void visOrdreNummer(int ordrenummer);

    public String visMenuKort(ArrayList<Pizza> menukort);
    
    public void visMenuKortDB();

    public void visHovedMenu();
    
    public void visStartMenu();

    public String hovedMenuValg();

    public void visOrdreHistorik();

    public void visAktiveOrdrer(ArrayList<Bestilling>aktiveOrdrer);

    public void redigerBestilling(ArrayList<Bestilling> aktiveOrdrer);

    public String fjernBestilling(ArrayList<Bestilling> aktiveOrdrer);
    
    public void færdiggørBestilling(ArrayList<Bestilling>aktiveOrdrer);
    
    public void redigerBestillingsMenu(); 
    
    public boolean MerePizza(); 

    public void fjernBestillingDB();

    public void færdiggørBestillingDB();
    
    public void visHovedMenuDB();

    public void visOrdrehistorikDB();
    
    
    
    
}
