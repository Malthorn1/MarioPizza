package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    
    public Connection connector() {
        Connection connection = null;
        try {
            String user = "root";
            String password = "mixe91decoys";
            String IP = "localhost";
            String PORT = "3306";
            String DATABASE = "mario";
            String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE;
                    /*+ "?useUnicode=true&useJDBCcompliantTimezoneShift=true&"
                    + "useLegacyDatetimeCode=false&"
                    + "serverTimezone=UTC"; */

            connection = DriverManager.getConnection(url, user, password);
            
            
        } catch (Exception e) {
        }
        //Returnerer en connection vi kan bruge hver gang vi laver et metode kald
        return connection;
    }

    public void printMenukort() throws SQLException {
        Connection connection = connector();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM pizzaer");
        while (result.next()) {
            int pizzanummer = result.getInt(1);
            String pizzanavn  = result.getNString(2);
            int pizzapris = result.getInt(3);
            System.out.print(pizzanummer + " ");
            System.out.print(pizzanavn + " ");
            System.out.print(pizzapris+"\n");
            
        }
    }

} 
    


