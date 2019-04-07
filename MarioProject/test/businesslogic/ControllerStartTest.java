package businesslogic;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author Casper P, Frederik, Mikkel
 */

public class ControllerStartTest {

    @Test
    public void testStart() {
        //Arrange
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0));
        menukort.add(new Pizza(2, "Amerikaner", 53.0));
        menukort.add(new Pizza(3, "Cacciatore", 57.0));
        //start() metode kaldes i controller, herefter vælges
        // 2 i menuen som er "opretBestilling" ekstra input er "q"
        // ellers bliver menuen ved med at loope
        String[] input = {"2","2","q"}; // test input data
        FakeUI ui = new FakeUI(input);
        Controller ctrl = new Controller(ui, menukort);
        
        //act
        ctrl.start();
        //assert
        assertTrue(ui.output.get(3).contains("3"));
        assertTrue(ctrl.getAktiveOrdrer().size()==1);
    }

}
