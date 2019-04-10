package businesslogic;

import datalayer.DB;
import datalayer.FileFacade;
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
    private DB db;
    private UI ui;
    private ArrayList<Pizza> menukort;
    private ArrayList<Bestilling> aktiveOrdrer;
    private int currentOrderNum;

    public Controller(UI ui, ArrayList<Pizza> menukort) {
        this.db=db;
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
                    System.err.print("Input forkert, prøv igen: ");
            }
        } while (!quit);

    }
    //Problemet her er at vi ikke kunne få færdiggør ordre til at virke, så vi skriver til ordre
    //historikken så snart vi laver en bestilling. 
    public void opretBestilling() {
        int pizzaNummer = ui.vælgPizza();
        int pizzanummer2 = 0; 
  
        FileFacade ordre = new FileFacade();
        Bestilling bestilling = new Bestilling(menukort.get(pizzaNummer - 1), currentOrderNum);
        //Tilføj bestilling til ordrelisten
        aktiveOrdrer.add(bestilling);
         
        while(ui.MerePizza() == true) {
            pizzanummer2 = ui.vælgPizza();
            Bestilling bestilling2 = new Bestilling(menukort.get(pizzanummer2 - 1), currentOrderNum);
             aktiveOrdrer.add(bestilling2);
        } 
        
        // Vis ordrenummer på skærm
        ui.visOrdreNummer(currentOrderNum);
        //Viser pizzavalg
        //ui.visPizzaValg(bestilling.getPizza().toString());
        ui.visPizzaValg(aktiveOrdrer.toString());
        
        
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
        ui.redigerBestillingsMenu(); 
        Scanner scan = new Scanner(System.in);
        String brugerInput = scan.nextLine();
        switch(brugerInput){
            case "1":
                ui.redigerBestilling(aktiveOrdrer);
                break;
            case "2":
                fjernBestilling();
                break;
            case"3":
                ui.færdiggørBestilling(aktiveOrdrer);
                break;
            case "q":
                ui.visHovedMenu();
            default:
                System.err.print("Input forkert, prøv igen: ");
        }
        
        
        
    }
    public void fjernBestilling(){
        ui.fjernBestilling(aktiveOrdrer);
    }
    public void visAktiveOrdrer() {
        ui.visAktiveOrdrer(aktiveOrdrer);
        
    }

    public void visOrdreHistorik() {
        ui.visOrdreHistorik();
    }

}
