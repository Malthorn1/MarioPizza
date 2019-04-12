package businesslogic;

import datalayer.Facade;
import datalayer.FileFacade;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import presentation.UI;

/**
 *
 * @author Casper P, Frederik, Mikkel
 */

public class Controller {
    private Facade facade;
    private UI ui;
    private ArrayList<Pizza> menukort;
    private ArrayList<Bestilling> aktiveOrdrer;
    private int currentOrderNum;

    public Controller(UI ui, ArrayList<Pizza> menukort) {
        this.facade=facade;
        this.ui = ui;
        this.menukort = menukort;
        aktiveOrdrer = new ArrayList<Bestilling>();
        currentOrderNum = 1;
    }
    
    public void startProgram() throws SQLException {
        boolean quit = false;
        ui.visStartMenu();
        do {
            String brugerInput = ui.hovedMenuValg();
            switch (brugerInput) {
                case "1":
                    start();
                    break;
                case "2":
                    startDB();
                    break;
                case "q":
                    quit = true;
                    break;
                default:
                    System.err.print("Input forkert, prøv igen: ");
            }
        } while (!quit);
    }

    public void start() {
        boolean quite = false;
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
                    Runtime.getRuntime().exit(0);
                    break;
                default:
                    System.err.print("Input forkert, prøv igen: ");
            }
        } while (!quite);

    }
    
        public void startDB() throws SQLException {
        boolean quit = false;
        ui.visHovedMenuDB();
        do {
            String brugerInput = ui.hovedMenuValg();
            switch (brugerInput) {
                case "1":
                    ui.visMenuKortDB();
                    break;
                case "2":
                    ui.vælgPizzaDB();
                    break;
                case "3":
                   ui.visAktiveOrdreDB();
                    break;
                case "4":
                    ui.fjernBestillingDB();
                    break;
                case "5":
                    ui.færdiggørBestillingDB();
                    break;
                case "6":
                    ui.visOrdrehistorikDB();
                    break;
                case "q":
                    Runtime.getRuntime().exit(0);
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
        int pizzanummer2;
  
        FileFacade ordre = new FileFacade();
        
        if (pizzaNummer <1 || pizzaNummer  > 13 ) {
            ui.visHovedMenu();
        }  else {
        Bestilling bestilling = new Bestilling(menukort.get(pizzaNummer - 1), currentOrderNum);
        aktiveOrdrer.add(bestilling);
        //Tilføj bestilling til ordrelisten
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
        }
            
       //Kunne ikke lave nogle Junit test da vi havde "start()" her grundet vi fik en arrayoutofboundsexception
        
        
    }

    
    public ArrayList<Bestilling> getAktiveOrdrer() {
        return aktiveOrdrer;
    }
    
    public void visMenukort() {
        ui.visMenuKort(menukort);
    }
    
    public void visMenukortDB() throws SQLException {
        ui.visMenuKortDB();
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
                ui.fjernBestilling(aktiveOrdrer);
                break;
            case"3":
                ui.færdiggørBestilling(aktiveOrdrer);
                break;
            case "q":
                ui.visHovedMenu();
                break;
            default:
                System.err.print("Input forkert, prøv igen: ");
        } 
    }
        
        public void visAktiveOrdrer() {
        ui.visAktiveOrdrer(aktiveOrdrer);
        }

        public void visOrdreHistorik() {
        ui.visOrdreHistorik();
        }

}
