
package businesslogic;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author prejl
 */
public class VisOrdreHistorikTest {
    
    

    @Test
    public void testVisOrdreHistorik() {
        //Arrange
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0));
        menukort.add(new Pizza(2, "Amerikaner", 53.0));
        menukort.add(new Pizza(3, "Cacciatore", 57.0));
        String[] input = {"2","3", "q"}; // test input data
        FakeUI ui = new FakeUI(input);
        Controller ctrl = new Controller(ui, menukort);
        
        //act
        ctrl.start();
        ctrl.visOrdreHistorik();
        //assert
        
    }
}
