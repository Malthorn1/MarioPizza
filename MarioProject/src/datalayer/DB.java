
package datalayer;

import java.sql.SQLException;

/**
 *
 * @author prejl
 */
public interface DB {
    
    public void opretBestilling();
    
    public void printMenukort() throws SQLException;
    
    public void fjernBestilling();
    
}
