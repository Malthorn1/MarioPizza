package businesslogic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import presentation.UI;

/**
 *
 * @author Casper P, Frederik, Mikkel
 */

public class Controller {

    private UI ui;
    private ArrayList<Pizza> menukort;
    private ArrayList<Bestilling> aktiveOrdrer;
    private int currentOrderNum;

    public Controller(UI ui, ArrayList<Pizza> menukort) {
        this.ui = ui;
        this.menukort = menukort;
        aktiveOrdrer = new ArrayList<Bestilling>();
        currentOrderNum = 1;
    }

    public void start() {
        boolean quit = false;
        ui.visHovedMenu();
        do {
            String brugerInput = ui.hovedMenuValg();
            switch (brugerInput) {
                case "1":
                    visMenukort();
                    break;
                case "2":
                    opretBestilling();
                    break;
                case "3":
                    redigerBestilling();
                    break;
                case "4":
                    visAktiveOrdrer();
                    break;
                case "5":
                    visOrdreHistorik();
                    break;
                case "q":
                    quit = true;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } while (!quit);

    }
    //Problemet her er at vi ikke kunne få færdiggør ordre til at virke, så vi skriver til ordre
    //historikken så snart vi laver en bestilling. 
    public void opretBestilling() {
        int pizzaNummer = ui.vælgPizza();
        FileFacade ordre = new FileFacade();
        Bestilling bestilling = new Bestilling(menukort.get(pizzaNummer - 1), currentOrderNum);
        //Tilføj bestilling til ordrelisten
        aktiveOrdrer.add(bestilling);
        // Vis ordrenummer på skærm
        ui.visOrdreNummer(currentOrderNum);
        //Viser pizzavalg
        ui.visPizzaValg(bestilling.getPizza().toString());
        currentOrderNum++;
        try{
            ordre.writeFile(bestilling);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
            
       //Kunne ikke lave nogle Junit test da vi havde "start()" her grundet vi fik en arrayoutofboundsexception
        
        
    }

    
    public ArrayList<Bestilling> getAktiveOrdrer() {
        return aktiveOrdrer;
    }
    
    public void visMenukort() {
        ui.visMenuKort(menukort);
    }

    public void redigerBestilling() {
        System.out.println("1: Rediger en ordre");
        System.out.println("2: Fjern en ordre");
        System.out.println("3: Færdiggør en ordre");
        System.out.println("q: Tilbage til hovedmenu");
        Scanner scan = new Scanner(System.in);
        String brugerInput = scan.nextLine();
        switch(brugerInput){
            case "1":
                ui.redigerBestilling(aktiveOrdrer);
                break;
            case "2":
                ui.fjernBestilling(aktiveOrdrer);
                break;
            case"3":
                ui.færdiggørBestilling(aktiveOrdrer);
                break;
            case "q":
                ui.visHovedMenu();
            default:
                throw new IllegalArgumentException();
        }
        
        
        
    }

    public void visAktiveOrdrer() {
        ui.visAktiveOrdrer(aktiveOrdrer);
        
    }

    public void visOrdreHistorik() {
        ui.visOrdreHistorik();
    }

}
