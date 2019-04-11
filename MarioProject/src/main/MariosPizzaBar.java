package main;

import businesslogic.Controller;
import businesslogic.Pizza;
import java.util.ArrayList;
import presentation.FakeUI;
import presentation.SystemUI;
import presentation.UI;
import datalayer.Database;
import java.sql.SQLException;

/**
 *
 * @author Casper P, Frederik, Mikkel
 */
public class MariosPizzaBar {
    public static void main(String[] args) throws SQLException {
        ArrayList<Pizza> pizzaer = new ArrayList();
        pizzaer.add(new Pizza(1, "Vesuvio", 57.0));
         pizzaer.add(new Pizza(2, "Amerikaner", 53.0));
         pizzaer.add(new Pizza(3, "Cacciatore", 57.0));
         pizzaer.add(new Pizza(4, "Carbona", 63.0));
         pizzaer.add(new Pizza(5, "Dennis", 65.0));
         pizzaer.add(new Pizza(6, "Bertil", 57.0));
         pizzaer.add(new Pizza(7, "Silvia", 61.0));
         pizzaer.add(new Pizza(8, "Victoria", 61.0));
         pizzaer.add(new Pizza(9, "Toronfo", 61.0));
         pizzaer.add(new Pizza(10, "Capricciosa", 61.0));
         pizzaer.add(new Pizza(11, "Hawai", 61.0));
         pizzaer.add(new Pizza(12, "Le Blissola", 61.0));
         pizzaer.add(new Pizza(13, "Venezia", 61.0));
         pizzaer.add(new Pizza(14, "Mafia", 61.0));
        SystemUI ui = new SystemUI();
        Controller ctrl = new Controller(ui, pizzaer);
        Database db = new Database();
        ctrl.startProgram();
        //db.printMenukort();
        
        //db.opretBestilling(db.getPizza(2));
////        System.out.println(db.getPizza(2));
        //db.opretBestilling(db.getPizza(5));
        //db.opretBestilling(db.getPizza(7));
//        db.printMenukort();
////        db.opretBestilling(db.getPizza(9));
////        db.opretBestilling(db.getPizza(13));
////        //db.printMenukort();
        
        
    }
}
