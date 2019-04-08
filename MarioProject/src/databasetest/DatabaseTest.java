package databasetest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DatabaseTest {
    
    public static void main(String[] args) throws Exception {
        
        String user = "root";
        String password = "frb150195";
        String IP = "localhost";
        String PORT = "3306";
        String DATABASE = "MarrioPizza";
        String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE;

                
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM pizzaer");
        while (result.next()){
        int resultat = result.getInt(1);
        System.out.println(resultat);
        }
    }
    
}
