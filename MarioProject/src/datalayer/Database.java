package datalayer;

import businesslogic.Pizza;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database implements DB {

    public Connection connector() {
        Connection connection = null;
        try {
            String user = "root";
            String password = "frb150195";
            String IP = "localhost";
            String PORT = "3306";
            String DATABASE = "mario";
            String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE
            + "?useUnicode=true&useJDBCcompliantTimezoneShift=true&"
                    + "useLegacyDatetimeCode=false&"
                    + "serverTimezone=UTC";

            connection = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
        }
        //Returnerer en connection vi kan bruge hver gang vi laver et metode kald
        return connection;
    }

    @Override
    public void printMenukort() throws SQLException {
        Connection connection = connector();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM pizzaer");
        while (result.next()) {
            int pizzanummer = result.getInt(1);
            String pizzanavn = result.getNString(2);
            int pizzapris = result.getInt(3);
            System.out.print(pizzanummer + " ");
            System.out.print(pizzanavn + " ");
            System.out.print(pizzapris + "\n");

        }
    }

    @Override
    public void opretBestilling() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        Connection connection = connector();
//        
//        
//        try {
//            String sql = "INSERT INTO aktiveOrdrer(ORDRENUMMER, FÆRDIG, DATOOPRETTET, DATOFÆRDIG, PIZZANUMMER)VALUES(?,?,?,?,?)";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setInt(2, ORDRENUMMER);
//            statement.setString(2, pizza.getPizzaNavn());
//            statement.setDouble(3, pizza.getPris());
//            statement.executeUpdate();
//
//        } catch (SQLException e) {
//
//        }
        Scanner scanner = new Scanner(System.in);
            try {
                Connection connection = connector();
                Statement statement = connection.createStatement();
                int PIZZANUMMER=scanner.nextInt();
                statement.executeUpdate("insert into AKTIVEORDRER value('"+PIZZANUMMER+"')");
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            //https://www.youtube.com/watch?v=c139qtuK2_s 
        }
    


    @Override
    public void fjernBestilling() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
