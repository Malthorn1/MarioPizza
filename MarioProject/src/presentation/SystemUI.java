package presentation;

import businesslogic.Bestilling;
import datalayer.FileFacade;
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
    public int vælgPizza()  {
        /*Scanner scan = new Scanner(System.in);
        return scan.nextInt();*/
        Scanner scan = new Scanner(System.in);
        boolean running = true;
        int result= 0;
        System.out.println("Skriv hvilket pizzanummer kunden har bestilt. Skriv q for at gå tilbage.");
        String brugerInput = scan.next();
        if (brugerInput.contains("q")) {
            return 0;
        } else if (brugerInput.matches("[1-9][0-9]*")) {
            result=Integer.valueOf(brugerInput);
            while (running) {
                result = Integer.parseInt(brugerInput);
            if (result > 0 && result < 14) {
                break;
            } else {
                System.err.println("Pizzaen findes ikke, går tilbage til hovedmenu. ");
                System.out.println("");
                return result;
             }
         }
    }
        return result;
    }

    @Override
    public void visPizzaValg(String str) {
        String[] arrOfStr = str.split("  ,");
        for (String a : arrOfStr) {
            System.out.println(a);
        }
        //System.out.println(str);
        visHovedMenu();

    }

    @Override
    public void visOrdreNummer(int ordrenummer) {
        System.out.println("Kunden har fået ordrenummer: " + ordrenummer);
    }

    @Override
    public String visMenuKort(ArrayList<Pizza> menukort) {
        for (Pizza pizza : menukort) {
            System.out.println(pizza.toString());
        }
        System.out.println("\nTryk q for at gå tilbage");
        return getQ();
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
        }
        Scanner scan = new Scanner(System.in);
        System.out.print("\nTryk q for returnere til hovedmenu: ");
        String brugerInput = scan.next();
        if (brugerInput.contains("q")) {
            visHovedMenu();
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

    }

    @Override
    public String fjernBestilling(ArrayList<Bestilling> aktiveOrdrer) {
        System.out.println("Indtast ordrenummer på ordren du ønsker at slette");
        System.out.println("Skriv q for at gå tilbage til rediger bestilling");
        Scanner scan = new Scanner(System.in);
        String brugerInput = scan.nextLine();
//        String brugerInput1 = "ordreNummer: "+ brugerInput1;
        //Hvis bruger ønsker at afslutte
        if (brugerInput.equals("q")) {
            redigerBestilling(aktiveOrdrer);
        }
        //Hvis bruger ikke ønsker at afslutte kører for loopet og søge gennem aktiveOrdrer array
        //Loopet kigger på hvert index plads i array og tjekker om hvorvidt den bestilling der er på indexet's
        //toString metode indeholder brugerInput. Hvis den indeholder brugerInput, bliver det index fjernet
        for (int i = 0; i < aktiveOrdrer.size(); i++) {
            if (aktiveOrdrer.get(i).toString().contains(brugerInput)) {
                System.out.println("Du har fjernet ordre: " + aktiveOrdrer.get(i));
                aktiveOrdrer.remove(i);
                visHovedMenu();
            }
            {
                System.err.println("Forkert input, går tilbage til hovedmenu");
            }
        }
        return "";
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
            } else {
                System.err.println("Forkert input, går tilbage til hovedmenu");
            }
            visHovedMenu();
        }
    }

    public String getQ() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        if (input.equals("q")) {
            visHovedMenu();
        } else if (input != "q") {
            System.err.print("Input forkert, prøv igen: ");
            return getQ();
        }
        return "";
    }

    @Override
    public void redigerBestillingsMenu() {
        System.out.println("1: Rediger en ordre");
        System.out.println("2: Fjern en ordre");
        System.out.println("3: Færdiggør en ordre");
        System.out.println("Skriv q for at gå tilbage til hovedmenu");
    }

    @Override
    public boolean MerePizza() {
        System.out.println("Vil kunden have mere pizza? tast 1 for ja, tryk alt andet for nej");
        Scanner scan = new Scanner(System.in);
        String brugerInput = scan.nextLine();
        String Invalid;

        if (brugerInput.equals("1")) {
            return true;
        } else {
            return false;
        }

    }
}
