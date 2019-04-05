/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author Casper P, Frederik, Mikkel
 */

public class VisAktiveOrdrerTest {
    @Test
    public void testVisAktiveOrdrer() {
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0));
        menukort.add(new Pizza(2, "Amerikaner", 53.0));
        menukort.add(new Pizza(3, "Cacciatore", 57.0));
        
        String[] input = {"2", "3", "q"}; // test input data
        FakeUI ui = new FakeUI(input);
        Controller ctrl = new Controller(ui, menukort);

        //act
        ctrl.start();
        ctrl.visAktiveOrdrer();
        
        
        //assert
        assertTrue(ui.output.get(0).contains("muligheder"));
        //assertTrue(ctrl.getAktiveOrdrer().size()==4);
        assertTrue(ui.output.get(1).contains("Amerikaner"));
        
    }

}
