package businesslogic;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author prejl
 */
public class visMenukortTest {
    
    @Test
    public void testVisMenukort(){
        //arrange
        ArrayList<Pizza> menukort = new ArrayList();
        menukort.add(new Pizza(1, "Vesuvio", 57.0));
        menukort.add(new Pizza(2, "Amerikaner", 53.0));
        menukort.add(new Pizza(3, "Cacciatore", 57.0));
        String[] input = {""}; // test input data
        FakeUI ui = new FakeUI(input);
        Controller ctrl = new Controller(ui, menukort);
        
        //act
        ctrl.visMenukort();
        
        assertTrue(ui.output.get(0).contains("Vesuvio"));
        assertTrue(ui.output.get(1).contains("Amerikaner"));
        assertTrue(ui.output.get(2).contains("Cacciatore"));
        
    }
}
