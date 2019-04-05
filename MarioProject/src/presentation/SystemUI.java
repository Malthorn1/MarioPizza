package presentation;

import businesslogic.Bestilling;
import businesslogic.FileFacade;
import businesslogic.Pizza;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Casper P, Frederik, Mikkel
 */


public class SystemUI implements UI {

    @Override
    public int vælgPizza() {
        System.out.println("Skriv hvilket pizzanummer kunden har bestilt");
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    @Override
    public void visPizzaValg(String str) {
        System.out.println(str);

    }

    @Override
    public void visOrdreNummer(int ordrenummer) {
        System.out.println("Kunden har fået ordrenummer: " + ordrenummer);
    }

    @Override
    public void visMenuKort(ArrayList<Pizza> menukort) {
        for (Pizza pizza : menukort) {
            System.out.println(pizza.toString());
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("\nTryk q for at gå tilbage");
        String brugerInput = scan.nextLine();
        if (brugerInput.contains("q"))
            visHovedMenu();
        else
            System.out.println("Hvis du vil tilbage så tryk q");
    }

    @Override
    public void visHovedMenu() {
        System.out.println("");
        System.out.println("Vælg en af følgende valgmuligheder: ");
        System.out.println("1: Vis menukort");
        System.out.println("2: Opret bestilling");
        System.out.println("3: Rediger bestilling");
        System.out.println("4: Vis aktive ordrer");
        System.out.println("5: Vis ordrehistorik");
        System.out.println("q: Afslut");
    }

    @Override
    public String hovedMenuValg() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    @Override
    public void visOrdreHistorik() {
        FileFacade ordrehistorik = new FileFacade();
        try {
            ordrehistorik.visOrdreHistorik();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }

    @Override
    //For each loop der printer hver bestilling i aktiveOrdrer Array
    public void visAktiveOrdrer(ArrayList<Bestilling> aktiveOrdrer) {
        for (Bestilling bestilling : aktiveOrdrer) {
            System.out.println(bestilling.toString());
        }
        visHovedMenu();
    }

    //Kan ikke finde ud af at sætte et nyt pizza objekt.
    @Override
    public void redigerBestilling(ArrayList<Bestilling> aktiveOrdrer) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Indtast odrenummeret på den bestilling du ønsker at ændre: ");
        System.out.println("q for at gå tilbage");
        String brugerInput = scan.nextLine();
        if (brugerInput.contains("q")) {
            hovedMenuValg();
        }

    }

    @Override
    public void fjernBestilling(ArrayList<Bestilling> aktiveOrdrer) {
        System.out.println("Indtast ordrenummer på ordren du ønsker at slette");
        System.out.println("q for at gå tilbage til rediger bestilling");
        Scanner scan = new Scanner(System.in);
        String brugerInput = scan.nextLine();
        //Hvis bruger ønsker at afslutte
        if (brugerInput.contains("q")) {
            redigerBestilling(aktiveOrdrer);
        }
        //Hvis bruger ikke ønsker at afslutte kører for loopet og søge gennem aktiveOrdrer array
        //Loopet kigger på hvert index plads i array og tjekker om hvorvidt den bestilling der er på indexet's
        //toString metode indeholder brugerInput. Hvis den indeholder brugerInput, bliver det index fjernet
        for (int i = 0; i < aktiveOrdrer.size(); i++) {
            if (aktiveOrdrer.get(i).toString().contains(brugerInput)) {
                System.out.println("Du har fjernet ordre: " + aktiveOrdrer.get(i));
                aktiveOrdrer.remove(i);

            }
        }
        visHovedMenu();
    }

    //Har lavet denne metode for at tilføje til ordrehistorik når man færdiggør en ordre,
    //Fremfor at gøre det i controlleren når man opretter en bestilling.
    //Kan ikke finde ud af at skrive til vores FileFacade fra SystemUI
    @Override
    public void færdiggørBestilling(ArrayList<Bestilling> aktiveOrdrer) {
        FileFacade ordre = new FileFacade();
        System.out.println("Hvilken ordre ønsker du at færdiggøre?");
        Scanner scan = new Scanner(System.in);
        String brugerInput = scan.nextLine();

        for (int i = 0; i < aktiveOrdrer.size(); i++) {
            if (aktiveOrdrer.get(i).toString().contains(brugerInput)) {
                System.out.println("Du har fædriggjort ordre: " + aktiveOrdrer.get(i));

                aktiveOrdrer.remove(i);

            }

//        try{
//            ordre.writeFile(brugerInput);
//        }
//        catch(FileNotFoundException e){
//            e.printStackTrace();
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//            
            visHovedMenu();
        }

    }
}
