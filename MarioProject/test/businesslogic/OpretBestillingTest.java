package businesslogic;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author prejl
 */
public class OpretBestillingTest {

    public OpretBestillingTest() {
    }

    @Test
    public void OpretBestillingEnPizza() {

        //Arrange
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57));
        menukort.add(new Pizza(2, "Amerikaner", 53));

        String[] input = {"2"}; // test input data
        FakeUI ui = new FakeUI(input);
        Controller ctrl = new Controller(ui, menukort);
        //Act
        ctrl.opretBestilling();
        //Assert
        assertEquals("1", ui.output.get(1));
        
        assertTrue(ui.output.get(2).contains("Amerikaner"));
        assertTrue(ctrl.getAktiveOrdrer().size()==1);
        assertEquals("Amerikaner", ctrl.getAktiveOrdrer().get(0).getPizza().getPizzaNavn());
        

    }

    @Test
    public void OpretBestillingToPizzaer(){
        //Arrange
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57));
        menukort.add(new Pizza(2, "Amerikaner", 53));

        String[] input = {"1", "2"}; // test input data
        FakeUI ui = new FakeUI(input);
        Controller ctrl = new Controller(ui, menukort);
        //Act
        
        ctrl.opretBestilling();
        ctrl.opretBestilling();
        //Assert
        assertEquals("1", ui.output.get(1));
        
        //assertTrue(ui.output.get(2).contains("Amerikaner"));
        assertTrue(ctrl.getAktiveOrdrer().size()==2);
        //assertEquals("Amerikaner", ctrl.getAktiveOrdrer().get(0).getPizza().getPizzaNavn());
        
    }
}
