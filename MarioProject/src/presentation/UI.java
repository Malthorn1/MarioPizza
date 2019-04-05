package presentation;

import businesslogic.Bestilling;
import businesslogic.Pizza;
import java.util.ArrayList;

/**
 *
 * @author Casper P, Frederik, Mikkel
 */


public interface UI {

    public int vælgPizza();
    public void visPizzaValg(String string);

    public void visOrdreNummer(int ordrenummer);

    public void visMenuKort(ArrayList<Pizza> menukort);

    public void visHovedMenu();

    public String hovedMenuValg();

    public void visOrdreHistorik();

    public void visAktiveOrdrer(ArrayList<Bestilling>aktiveOrdrer);

    public void redigerBestilling(ArrayList<Bestilling> aktiveOrdrer);

    public void fjernBestilling(ArrayList<Bestilling> aktiveOrdrer);
    
    public void færdiggørBestilling(ArrayList<Bestilling>aktiveOrdrer);
    
    
}
