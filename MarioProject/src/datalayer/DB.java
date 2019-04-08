
package datalayer;

import businesslogic.Pizza;
import java.sql.SQLException;

/**
 *
 * @author prejl
 */
public interface DB {
    
    public void opretBestilling(Pizza pizza);
    
    public void printMenukort() throws SQLException;
    
    public void fjernBestilling();
    
}
