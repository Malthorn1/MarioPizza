
package datalayer;

import businesslogic.Pizza;
import java.sql.SQLException;

/**
 *
 * @author prejl
 */
public interface Facade {
    
    public void opretBestilling(Pizza pizza)throws SQLException;
    
    public void printMenukort() throws SQLException;
    
    public void visAktiveOrdre() throws SQLException;
    
    public void fjernBestilling(Integer ordrenummer);
    
    public void færdiggørBestilling(Integer ordrenummer) throws SQLException;
    
    public void visOrdrehistorik() throws SQLException;
    
}
