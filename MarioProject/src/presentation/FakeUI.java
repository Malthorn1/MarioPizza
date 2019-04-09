package presentation;

import businesslogic.Bestilling;
import datalayer.FileFacade;
import businesslogic.Pizza;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Casper P, Frederik, Mikkel
 */
public class FakeUI implements UI {

    private String[] input;
    private int index = 0;
    public ArrayList<String> output = new ArrayList<>();

    public FakeUI(String[] input) {
        this.input = input;
    }

    @Override
    public void visPizzaValg(String str) {
        output.add(str);

    }

    @Override
    public int vælgPizza() {
        output.add("Skriv hvilket pizzanummer kunden har bestilt");
        return Integer.parseInt(input[index++]);
    }

    @Override
    public void visOrdreNummer(int ordrenummer) {
        output.add("" + ordrenummer);
    }

    @Override
    public String visMenuKort(ArrayList<Pizza> menukort) {
        for (Pizza pizza : menukort) {
            output.add(pizza.toString());
        }
        return "";
    }

    @Override
    public void visHovedMenu() {
        output.add("Vælg en af følgende valgmuligheder: ");
        output.add("1: Vis menukort");
        output.add("2: Opret bestilling");
        output.add("3: Afslut");
    }

    @Override
    public String hovedMenuValg() {
        return input[index++];
    }

    @Override
    public void visOrdreHistorik() {
        //Kan ikke finde ud af at lave en JUnit test på en filereader
    }

    @Override
    public void visAktiveOrdrer(ArrayList<Bestilling> aktiveOrdrer) {
        for (Bestilling bestilling : aktiveOrdrer) {
            output.add(bestilling.toString());
        }
    }

    @Override
    public void redigerBestilling(ArrayList<Bestilling> aktiveOrdrer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String fjernBestilling(ArrayList<Bestilling> aktiveOrdrer) {
        aktiveOrdrer.remove(0);
        return "";
    }

    @Override
    public void færdiggørBestilling(ArrayList<Bestilling> aktiveOrdrer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void redigerBestillingsMenu() {
        System.out.println("1: Rediger en ordre");
        System.out.println("2: Fjern en ordre");
        System.out.println("3: Færdiggør en ordre");
        System.out.println("q: Tilbage til hovedmenu");
    }

}
